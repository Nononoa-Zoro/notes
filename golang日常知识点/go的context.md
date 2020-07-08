### Context

#### 为什么会有context

在go的server端，一个请求对应一个goroutine处理，但是常常在一个goroutine中会开启另外的goroutine去调用其他的RPC方法或者连接数据库等操作。这些链式的操作通常需要一些全局的信息来标识一个用户（比如token,timeout等等），context的作用主要是在goroutine中传递上下文信息，包括deadline，cancelation signals（取消信号），K-V值等等。上游的Context发出取消的信号，可以及时关闭下游的routine以减少资源的浪费。

#### Context Struct 定义

```go
// Context携带了deadline,cancelation和请求域的值信息
// Context的方法可以同时被多个routine安全使用
type Context interface {
    // 当 context 被取消或者到了 deadline，返回一个被关闭的 channel
    Done() <-chan struct{}

    // Err indicates why this context was canceled, after the Done channel
    // is closed.
    // 当一个context被取消的时候返回错误信息
    Err() error

    // Deadline returns the time when this Context will be canceled, if any.
    // 返回Context被取消的时间
    Deadline() (deadline time.Time, ok bool)

    // Value returns the value associated with key or nil if none.
    // 返回Context中指定Key对应的Value
    Value(key interface{}) interface{}
}
```

```go
/* 多个 Goroutine 同时订阅 ctx.Done() 管道中的消息，一旦接收到取消信号就立刻停止当前正在执行的工作。*/
func main() {
	ctx, cancel := context.WithTimeout(context.Background(), 1*time.Second)

	defer cancel()

	go handle(ctx, 1500*time.Millisecond)

	select {
        //main routine关注ctx.Done的消息
		case<-ctx.Done():
			fmt.Println("main",ctx.Err())
	}


}

func handle(ctx context.Context, duration time.Duration) {
	select {
        //子routine关注Done的消息
		case <-ctx.Done():
			fmt.Println("handle", ctx.Err())
		case <-time.After(duration):
			fmt.Println("process request with", duration)
	}
}
```

