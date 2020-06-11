#### gRpc Server的创建流程

##### 1.创建一个gRpcServer

```go
gRpcServer := grpc.NewServer(options...)
```

grpc.NewServer返回一个grpc.Server类型的struct，定义如下：

```go
// Server is a gRPC server to serve RPC requests.
type Server struct {
    //// serverOptions 就是描述协议的各种参数选项，包括发送和接收的消息大小、buffer大小等等各种，跟 http Headers 类似
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

##### 2.server注册

server的注册调用的是通过proto文件生成的pb.go文件中的Register方法，第一个参数是第一步创建的gRpcServer，第二个参数是实现所有定义接口的struct示例

```go
pb.RegisterEmployeeServiceServer(gRpcServer, &employeeService{})
```

