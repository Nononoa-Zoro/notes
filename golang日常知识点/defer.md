#### defer关键字的特性

**特性一**

使用defer执行的函数会在当前函数体结束之后，逆序执行。

```go
func method(){
    defer func a(){}()
    defer func b(){}()
    
    return 
}
```

上面的代码会在函数return之后，首先执行b再执行a。



**特性二**

defer使用传值的方式传递参数的时候会进行预计算。

```go
func main(){
    startTime :=time.Now()
    //这样传递的startTime直接就是一个值拷贝，所以输出0s
	defer fmt.Println(time.Since(startTime))
    //匿名函数调用的方式可以解决这个问题
	defer func(start time.Time) {
		fmt.Println(time.Since(start))
	}(startTime)

	time.Sleep(time.Duration(1)*time.Second)
}
```



#### defer的数据结构

```go
//runtime包下
type _defer struct {
	siz     int32//参数和结果的内存大小
	started bool
	sp      uintptr//栈指针
	pc      uintptr//调用方的程序计数器
	fn      *funcval//defer中传入的函数
	_panic  *_panic
	link    *_defer
}
```

runtime._defer是一个Golang Defer Link调用链上的一个元素。个人觉得可以理解成一个单链表结构（每个 _defer结构中包含一个link字段）



#### 对于defer关键字

在编译的时候，defer关键字被转换为runtime.deferproc。在调用defer关键字的函数返回之前插入runtime.deferreturn。

在运行期间，runtime.deferproc会将一个新的runtime._defer结构体追加到当前Goroutine的链表头。runtime.deferreturn会从Goroutine的链表中从头开始依次拿数据。