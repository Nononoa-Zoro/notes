



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
func (s *Server) RegisterService(sd *ServiceDesc, ss interface{}) {
	ht := reflect.TypeOf(sd.HandlerType).Elem()
	st := reflect.TypeOf(ss)
	if !st.Implements(ht) {
		grpclog.Fatalf("grpc: Server.RegisterService found the handler of type %v that does not satisfy %v", st, ht)
	}
	s.register(sd, ss)
}
****************************************
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

写过socket编程的同学大都会发现server端通常是有一个for循环监听客户端的请求。

```go
func (s *Server) Serve(lis net.Listener) error {
...省略部分代码

	for {
		rawConn, err := lis.Accept()
		if err != nil {
			if ne, ok := err.(interface {
				Temporary() bool
			}); ok && ne.Temporary() {
				if tempDelay == 0 {
					tempDelay = 5 * time.Millisecond
				} else {
					tempDelay *= 2
				}
				if max := 1 * time.Second; tempDelay > max {
					tempDelay = max
				}
				s.mu.Lock()
				s.printf("Accept error: %v; retrying in %v", err, tempDelay)
				s.mu.Unlock()
				timer := time.NewTimer(tempDelay)
				select {
				case <-timer.C:
				case <-s.quit.Done():
					timer.Stop()
					return nil
				}
				continue
			}
			s.mu.Lock()
			s.printf("done serving; Accept = %v", err)
			s.mu.Unlock()

			if s.quit.HasFired() {
				return nil
			}
			return err
		}
		tempDelay = 0
		// Start a new goroutine to deal with rawConn so we don't stall this Accept
		// loop goroutine.
		//
		// Make sure we account for the goroutine so GracefulStop doesn't nil out
		// s.conns before this conn can be added.
		s.serveWG.Add(1)
		go func() {
			s.handleRawConn(rawConn)
			s.serveWG.Done()
		}()
	}
}

```

重点看一下这个handleRawConn函数，为了不阻塞Accept，这里是在for循环中开启一个新的routine调用这个函数。下面看看这个函数干了什么，

```go
//handleRawConn派生一个goroutine来处理刚刚接受的，尚未对其执行任何I / O的连接。
func (s *Server) handleRawConn(rawConn net.Conn) {
	if s.quit.HasFired() {
		rawConn.Close()
		return
	}
    //设置connection的deadline
	rawConn.SetDeadline(time.Now().Add(s.opts.connectionTimeout))
    //httphandshake grpc底层还是基于http2.0的
	conn, authInfo, err := s.useTransportAuthenticator(rawConn)
	if err != nil {
		// ErrConnDispatched means that the connection was dispatched away from
		// gRPC; those connections should be left open.
		if err != credentials.ErrConnDispatched {
			s.mu.Lock()
			s.errorf("ServerHandshake(%q) failed: %v", rawConn.RemoteAddr(), err)
			s.mu.Unlock()
			channelz.Warningf(s.channelzID, "grpc: Server.Serve failed to complete security handshake from %q: %v", rawConn.RemoteAddr(), err)
			rawConn.Close()
		}
		rawConn.SetDeadline(time.Time{})
		return
	}

	// Finish handshaking (HTTP2)
	st := s.newHTTP2Transport(conn, authInfo)
	if st == nil {
		return
	}

	rawConn.SetDeadline(time.Time{})
	if !s.addConn(st) {
		return
	}
	go func() {
		s.serveStreams(st)
		s.removeConn(st)
	}()
}
```

在建立完HTTP2.0的连接之后，又开启了一个routine执行serveStreams函数。

```go
func (s *Server) serveStreams(st transport.ServerTransport) {
	defer st.Close()
	var wg sync.WaitGroup
	st.HandleStreams(func(stream *transport.Stream) {
		wg.Add(1)
		go func() {
			defer wg.Done()
            //重点看下这个方法
			s.handleStream(st, stream, s.traceInfo(st, stream))
		}()
	}, func(ctx context.Context, method string) context.Context {
		if !EnableTracing {
			return ctx
		}
		tr := trace.New("grpc.Recv."+methodFamily(method), method)
		return trace.NewContext(ctx, tr)
	})
	wg.Wait()
}
```

```go
func (s *Server) handleStream(t transport.ServerTransport, stream *transport.Stream, trInfo *traceInfo) {
	...
	//根据servciceName去server中的map中找到对应handler处理
	srv, knownService := s.m[service]
	if knownService {
		if md, ok := srv.md[method]; ok {
            //sh.HandleRPC(stream.Context(), statsBegin) 调用handler处理rpc请求
			s.processUnaryRPC(t, stream, srv, md, trInfo)
			return
		}
		if sd, ok := srv.sd[method]; ok {
			s.processStreamingRPC(t, stream, srv, sd, trInfo)
			return
		}
	}

    ...
	
}
```

#### gRpc Client

##### 1.返回一个ClientConn。
```go
clientConn,err:=grpc.Dial("localhost:8000",grpc.WithInsecure())//得到客户端连接
client:=pb.NewClient(conn)//使用客户端生成的stub文件生成client
client.GetEmployeeByNo(context.TODO(), &pb.GetByRequest{
		No: int32(1),
	})//使用client调用rpc方法
```