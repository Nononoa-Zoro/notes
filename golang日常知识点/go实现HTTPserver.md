##### Handler

```go
//Handler接口定义了一个HTTP Server应该实现的方法 ServeHTTP
type Handler interface {
	ServeHTTP(ResponseWriter, *Request)
}
//go的HTTP服务都是基于Handler接口实现的，接受一个request作为输入参数，将处理后的数据通过response返回
```

```go
http.HandleFunc("/", indexHandler)
```

HandleFunc函数如下

```go
// DefaultServeMux is the default ServeMux used by Serve.
var defaultServeMux ServeMux
func HandleFunc(pattern string, handler func(ResponseWriter, *Request)) {
	DefaultServeMux.HandleFunc(pattern, handler)
}
```

```go
// ServeMux的HandlerFunc方法，实际调用的是Handle方法
func (mux *ServeMux) HandleFunc(pattern string, handler func(ResponseWriter, *Request)) {
	if handler == nil {
		panic("http: nil handler")
	}
	mux.Handle(pattern, HandlerFunc(handler))//这个HandlerFunc是一个函数，所以这里是在做类型转换 
    //type HandlerFunc func(ResponseWriter, *Request)
}
```

先看一下ServeMux的结构体定义

```go
type ServeMux struct {
	mu    sync.RWMutex
    m     map[string]muxEntry //muxEntry也是一个Handler，map的key就是路由，value就是对应的Handler(实现ServeHTTP方法)
	es    []muxEntry // slice of entries sorted from longest to shortest.
	hosts bool       // whether any patterns contain hostnames
}

type muxEntry struct {
	h       Handler
	pattern string
}
```



ServeMux的Handle方法的签名如下

```go
func (mux *ServeMux) Handle(pattern string, handler Handler) {
	mux.mu.Lock()
	defer mux.mu.Unlock()
	
	if pattern == "" {
		panic("http: invalid pattern")
	}
	if handler == nil {
		panic("http: nil handler")
	}
	if _, exist := mux.m[pattern]; exist {
		panic("http: multiple registrations for " + pattern)
	}

	if mux.m == nil {
		mux.m = make(map[string]muxEntry)
	}
    // 使用当前路由和handler创建muxEntry对象
	e := muxEntry{h: handler, pattern: pattern}
    // 向ServeMux维护的map中添加一个新的路由匹配规则
	mux.m[pattern] = e
    // 如果路由表达式以'/'结尾，则将对应的muxEntry对象加入到[]muxEntry中，按照路由表达式长度排序
	if pattern[len(pattern)-1] == '/' {
		mux.es = appendSorted(mux.es, e)
	}

	if pattern[0] != '/' {
		mux.hosts = true
	}
}
// Handle方法主要干了两件事儿 
// 1. 向ServeMux中的map增加给定的路由匹配规则，<pattern,handler>
// 2. 如果表达式以'/'结尾，则将对应的muxEntry对象加入到[]muxEntry中，按照路由表达式长度排序。
```

##### 开启服务

http.ListenAndServe ( )

```go
// 监听TCP连接，然后调用Serve方法处理连接。传入的handler一般为nil，如果是nil的话会采用默认的DefaultServeMux
func ListenAndServe(addr string, handler Handler) error {
	server := &Server{Addr: addr, Handler: handler}
	return server.ListenAndServe()
}
```

最终调用的是Server.serve ( ) 方法



```
func (srv *Server) Serve(l net.Listener) error {
   if fn := testHookServerServe; fn != nil {
      fn(srv, l) // call hook with unwrapped listener
   }

   origListener := l
   l = &onceCloseListener{Listener: l}
   defer l.Close()

   if err := srv.setupHTTP2_Serve(); err != nil {
      return err
   }

   if !srv.trackListener(&l, true) {
      return ErrServerClosed
   }
   defer srv.trackListener(&l, false)

   var tempDelay time.Duration // how long to sleep on accept failure

   baseCtx := context.Background()
   if srv.BaseContext != nil {
      baseCtx = srv.BaseContext(origListener)
      if baseCtx == nil {
         panic("BaseContext returned a nil context")
      }
   }

   ctx := context.WithValue(baseCtx, ServerContextKey, srv)
   for {
      rw, e := l.Accept()
      if e != nil {
         select {
         case <-srv.getDoneChan():
            return ErrServerClosed
         default:
         }
         if ne, ok := e.(net.Error); ok && ne.Temporary() {
            if tempDelay == 0 {
               tempDelay = 5 * time.Millisecond
            } else {
               tempDelay *= 2
            }
            if max := 1 * time.Second; tempDelay > max {
               tempDelay = max
            }
            srv.logf("http: Accept error: %v; retrying in %v", e, tempDelay)
            time.Sleep(tempDelay)
            continue
         }
         return e
      }
      connCtx := ctx
      if cc := srv.ConnContext; cc != nil {
         connCtx = cc(connCtx, rw)
         if connCtx == nil {
            panic("ConnContext returned nil")
         }
      }
      tempDelay = 0
      c := srv.newConn(rw)
      c.setState(c.rwc, StateNew) // before Serve can return
      go c.serve(connCtx)
   }
}
```

 

