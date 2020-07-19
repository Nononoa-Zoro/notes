



#### gRpc Server的创建流程

##### 1.创建一个gRpcServer

```go
gRpcServer := grpc.NewServer(options...)
```

grpc.NewServer返回一个grpc.Server类型的struct，定义如下：

```go
package grpc	

// 一个 Server 结构代表对外服务的单元，每个 Server 可以注册多个 Service，每个 Service 可以有多个方法。主程序需要实	例化 Server，然后注册 Service，最后调用 s.Serve() 开始接收请求。
type Server struct {
    // serverOptions 就是描述协议的各种参数选项，包括发送和接收的消息大小、buffer大小等等各种，跟 http Headers 类似
    // serverOptions还可以配置拦截器
	opts serverOptions
	
    //互斥锁
	mu     sync.Mutex // guards following
    
	lis    map[net.Listener]bool
	conns  map[transport.ServerTransport]bool
	serve  bool//server是不是在一个处理请求的状态
	drain  bool
	cv     *sync.Cond          // signaled when connections close for GracefulStop
    m      map[string]*service // service map : service name -> service info
	events trace.EventLog

	quit               *grpcsync.Event
	done               *grpcsync.Event
	channelzRemoveOnce sync.Once
	serveWG            sync.WaitGroup // counts active Serve goroutines for GracefulStop

	channelzID int64 // channelz unique identification number
	czData     *channelzData
}

```

**重点关注下 map[string]*service，map[net.Listener]bool，map[transport.ServerTransport]bool**

```go
// service consists of the information of the server serving this service and
// the methods in this service.
type service struct {
	server interface{} // the server for service methods
	md     map[string]*MethodDesc//MethodDesc就是一个包含方法名称和方法函数的struct
	sd     map[string]*StreamDesc//StreamDesc就是包含一个stream名称和流处理函数的struct
	mdata  interface{}
}

```

这个service struct 就是gRpc Server结构中的m这个map对应的value的结构。而RegisterServiceServer的作用也是为这个字段赋值。

下面我们来看看服务注册的源码实现。

##### 2.Service注册

server的注册调用的是通过proto文件生成的pb.go文件中的Register方法，第一个参数是第一步创建的gRpcServer，第二个参数是实现所有定义接口的struct实例

```go
//例子，向gRpc Server中注册一个实现了所有定义接口的service实体
pb.RegisterEmployeeServiceServer(gRpcServer, &employeeService{})
```

接着会调用proto文件对应生成的go文件的RegisterEmployeeServiceServer方法

方法签名如下：

```go
func RegisterEmployeeServiceServer(s *grpc.Server, srv EmployeeServiceServer) {
	s.RegisterService(&_EmployeeService_serviceDesc, srv)
}
//实际上调用的是grpc.server的RegisterService方法
```

```go
//判断传入的service是否实现了定义的所有接口
//第一个参数是服务描述信息
func (s *Server) RegisterService(sd *ServiceDesc, ss interface{}) {
	ht := reflect.TypeOf(sd.HandlerType).Elem()
	st := reflect.TypeOf(ss)
	if !st.Implements(ht) {
		grpclog.Fatalf("grpc: Server.RegisterService found the handler of type %v that does not satisfy %v", st, ht)
	}
	s.register(sd, ss)
}
****************************************
//服务描述信息的结构体定义
type ServiceDesc struct {
	ServiceName string
	// The pointer to the service interface. Used to check whether the user
	// provided implementation satisfies the interface requirements.
	HandlerType interface{}
	Methods     []MethodDesc
	Streams     []StreamDesc
	Metadata    interface{}
}
//下面是我的例子中的ServiceDesc
var _EmployeeService_serviceDesc = grpc.ServiceDesc{
	ServiceName: "pb.EmployeeService",//<package_name>.<rpc_service_name>
	HandlerType: (*EmployeeServiceServer)(nil),//一个指向Server的指针，用来检查用户注册的这个结构体是否实现了定义的Server的所有接口
    //Unary方法使用Method字段描述方法
	Methods: []grpc.MethodDesc{
		{
			MethodName: "GetEmployeeByNo",//方法名称和指定的函数
			Handler:    _EmployeeService_GetEmployeeByNo_Handler,
		},
		{
			MethodName: "Save",
			Handler:    _EmployeeService_Save_Handler,
		},
	},
    //流式调用的话使用streams来描述stream方法
	Streams: []grpc.StreamDesc{
		{
			StreamName:    "GetAll",
			Handler:       _EmployeeService_GetAll_Handler,
			ServerStreams: true,
		},
		{
			StreamName:    "AddAvatar",
			Handler:       _EmployeeService_AddAvatar_Handler,
			ClientStreams: true,
		},
		{
			StreamName:    "SaveAll",
			Handler:       _EmployeeService_SaveAll_Handler,
			ServerStreams: true,
			ClientStreams: true,
		},
	},
	Metadata: "Message.proto",//定义的proto文件名称
}

//与方法名称对应的处理函数 _EmployeeService_GetEmployeeByNo_Handler
func _EmployeeService_GetEmployeeByNo_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(GetByRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
        //没有设置拦截器就直接执行对应的方法
		return srv.(EmployeeServiceServer).GetEmployeeByNo(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/pb.EmployeeService/GetEmployeeByNo",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(EmployeeServiceServer).GetEmployeeByNo(ctx, req.(*GetByRequest))
	}
    //否则先执行拦截器方法
	return interceptor(ctx, in, info, handler)//参数：上下文，请求参数，相关方法的信息，对应的处理函数
}

//UnaryInterceptor定义
// UnaryServerInterceptor provides a hook to intercept the execution of a unary RPC on the server. info
// contains all the information of this RPC the interceptor can operate on. And handler is the wrapper
// of the service method implementation. It is the responsibility of the interceptor to invoke handler
// to complete the RPC.
//UnaryServerInterceptor提供了一个hook来拦截服务端unary RPC调的链式过程，
type UnaryServerInterceptor func(ctx context.Context, req interface{}, info *UnaryServerInfo, handler UnaryHandler) (resp interface{}, err error)

```

最终调用的是grpc Server的register方法

```go
func (s *Server) register(sd *ServiceDesc, ss interface{}) {
   s.mu.Lock()
   defer s.mu.Unlock()//加锁保证同一时间只有一个routine可以访问
   s.printf("RegisterService(%q)", sd.ServiceName)
   if s.serve {
       //如果还没注册服务就开始就监听的话就直接报错
      grpclog.Fatalf("grpc: Server.RegisterService after Server.Serve for %q", sd.ServiceName)
   }
   if _, ok := s.m[sd.ServiceName]; ok {
       //检查一个ServiceName是不是对应多个service
      grpclog.Fatalf("grpc: Server.RegisterService found duplicate service registration for %q", sd.ServiceName)
   }
   srv := &service{
      server: ss,
      md:     make(map[string]*MethodDesc),
      sd:     make(map[string]*StreamDesc),
      mdata:  sd.Metadata,
   }
   for i := range sd.Methods {
      d := &sd.Methods[i]
      srv.md[d.MethodName] = d
   }
   for i := range sd.Streams {
      d := &sd.Streams[i]
      srv.sd[d.StreamName] = d
   }
   s.m[sd.ServiceName] = srv
}
```

通过上面的代码可以看出，整个pb.RegisterEmployeeServiceServer方法的最终目的是对gRpcServer struct中的m赋值。（向m这个map中添加一个元素，它的key是servicename，它的value是server）。其实在server接受不同的rpc请求的时候就是根据不同的servicename找到对应的handler处理的。

##### 3.Serve方法

Serve的大致流程

在Serve方法中存在一个for循环，调用Accept()方法接受链接请求。

```go
for{
    //创建链接
    rawConn, err := lis.Accept()
    ...
    s.serveWG.Add(1)
		go func() {
            //对于每一个链接创建一个协程去处理请求
			s.handleRawConn(rawConn)
			s.serveWG.Done()
	}()
}
```

在handleRawConn（）方法中

```go
//handshake
conn, authInfo, err := s.useTransportAuthenticator(rawConn)
//完成握手建立HTTP2链接
st := s.newHTTP2Transport(conn, authInfo)
//真正开始处理请求的函数是在协程中调用serveStreams方法
go func() {
	s.serveStreams(st)
	s.removeConn(st)
}()
```

在serveStreams方法中，也是开启协程调用了handleStream开始真正处理这个请求

```go
go func() {
	defer wg.Done()
    //真正调用自己实现的server代码处理请求的 入口
	s.handleStream(st, stream, s.traceInfo(st, stream))
}()
```

```go
if knownService {
    //如果是已经注册的UnaryMethod服务，调用processUnaryRPC进行处理
	if md, ok := srv.md[method]; ok {
		s.processUnaryRPC(t, stream, srv, md, trInfo)
		return
	}
    //如果是已经注册的StreamMethod服务，调用processStreamingRPC进行处理
	if sd, ok := srv.sd[method]; ok {
		s.processStreamingRPC(t, stream, srv, sd, trInfo)
		return
	}
}
```

这里主要看下processUnaryRPC函数

```go
//md是传入的MethodDesc包括方法名称和方法，这行代码就是调用定义好的函数，所以我们只是实现了大佬规定好的接口，剩下的就交给rpc框架了。面向接口编程的方式，是模板方法设计模式的一种实现。
reply, appErr := md.Handler(srv.server, ctx, df, s.opts.unaryInt)
//将处理的结果写回到输出流
err := s.sendResponse(t, stream, reply, cp, opts, comp)
```

至此，简单的Server端Serve方法就分析完了。



#### gRpc Client

##### 1.client端的整体流程
```go
//整体流程
clientConn,err:=grpc.Dial("localhost:8000",grpc.WithInsecure())//得到客户端连接
client:=pb.NewClient(conn)//使用客户端生成的stub文件生成client
client.GetEmployeeByNo(context.TODO(), &pb.GetByRequest{
		No: int32(1),
})//使用client调用rpc方法
```



获取客户端连接的Dial方法

```go
//实际上调用的是DialContext方法
func Dial(target string, opts ...DialOption) (*ClientConn, error) {
	return DialContext(context.Background(), target, opts...)
}
```

DialContext方法

```go
//入参：上下文，目标地址，DialOption
func DialContext(ctx context.Context, target string, opts ...DialOption) (conn *ClientConn, err error) {
	...
    //将所有的client端的一元调用变为一个
    chainUnaryClientInterceptors(cc)
    //将所有的client端的stream调用合为一个
	chainStreamClientInterceptors(cc)
    ...
    //获取命名解析器
    resolverBuilder := cc.getResolver(cc.parsedTarget.Scheme)
    //如果没有配置就使用默认的
	if resolverBuilder == nil {
		channelz.Infof(cc.channelzID, "scheme %q not registered, fallback to default scheme", cc.parsedTarget.Scheme)
		cc.parsedTarget = resolver.Target{
			Scheme:   resolver.GetDefaultScheme(),//默认的协议名称就是"passthrough"
			Endpoint: target,
		}
		resolverBuilder = cc.getResolver(cc.parsedTarget.Scheme)
		if resolverBuilder == nil {
			return nil, fmt.Errorf("could not get resolver for default scheme: %q", cc.parsedTarget.Scheme)
		}
	}
    ...
    //构建一个resolver
    rWrapper, err := newCCResolverWrapper(cc, resolverBuilder)
}
```

client调用对应的stub中的方法

```go
func (c *employeeServiceClient) GetEmployeeByNo(ctx context.Context, in *GetByRequest, opts ...grpc.CallOption) (*EmployeeResponse, error) {
	out := new(EmployeeResponse)
    //ClientConn的Invoke方法是入口 入参分别是 上下文 方法名 输入 返回 客户端配置参数
	err := c.cc.Invoke(ctx, "/pb.EmployeeService/GetEmployeeByNo", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}
```



