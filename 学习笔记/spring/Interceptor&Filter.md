##Filter（过滤器）

Filter 依赖于 Servlet，它主要是针对 URL 地址做一个编码的事情。过滤掉没用的参数、简单的安全校验（比如登录不登录之类），而 Interceptor 依赖于 SpringMVC 框架，它是在 service 或者一个方法调用前，调用一个方法，或者在方法调用后 。



**Filter**: 过滤器是servlet定义的规范，是由**函数回调**实现的。在servlet前后使用，Servlet中的过滤器Filter是实现了javax.servlet.Filter接口的服务器端程序 。主要的用途是设置字符集、控制权限、控制转向、做一些业务逻辑判断等 。

![](d:\mdimage\filter.png)



## Interceptor（拦截器）

不是Servlet规范的。是属于**spring规范的**。Interceptor是基于java**反射机制**实现的，可以放在方法前后或者是异常抛出的前后。

主要用途：

1.登录拦截器

2.日志拦截器

3.权限拦截器

Interceptor在Dispatcher.doDispatch()方法中得到调用。

```java

// doDispatch源码(只保留关键代码)

protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ModelAndView mv = null;
            Exception dispatchException = null;

            try {
                ....
                其它的处理代码
                ....
                
                // 调用拦截器的前置处理方法
                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }

                // Actually invoke the handler.
                // 调用真正的处理请求的方法
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

                // 找到渲染模版
                applyDefaultViewName(processedRequest, mv);
                
                // 调用拦截器的后置处理方法
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            }
            catch (Exception ex) {
                ....
                异常处理代码
                ....
        }
        finally {
            ....
            始终要执行的代码
            ....
    }
```

`mapperHandler.applyPreHandle()`方法 

```java
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerInterceptor[] interceptors = getInterceptors();
        // 如果拦截器数组不为空
        if (!ObjectUtils.isEmpty(interceptors)) {
           // 按顺序调用拦截器数组中的preHandle方法
            for (int i = 0; i < interceptors.length; i++) {
                HandlerInterceptor interceptor = interceptors[i];
                // 如果拦截器的preHandle方法返回false, 则调用当前拦截器的triggerAfterCompletion方法, 然后返回, 并且不再调用后续的拦截器
                if (!interceptor.preHandle(request, response, this.handler)) {
                    triggerAfterCompletion(request, response, null);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }
```



## 二者区别

拦截器不依赖servlet，Filter依赖servlet容器

拦截器可以获取IOC中的bean，Filter不能

从以上分析可以看到过滤器和拦截器实现的方式的不同. `Filter`是利用了方法的调用(入栈出栈)完成整个流程, 而`Interceptor`是利用了`for`循环完成了整个流程.

 `Filter`的实现比较占用栈空间, 在`Filter`多的情况下可能会有栈溢出的风险存在.

 `Interceptor`的实现逻辑更加的清晰简单

 `Filter`组件更加的通用, 只要支持`java servlet`的容器都可以使用, 而`Interceptor`必须依赖于Spring

Filter的优先级是高于`Interceptor`, 即请求是先到`Filter`再到`Interceptor`的, 因为`Interceptor`的实现主体还是一个`servlet`

 

 

 

 

 



​	