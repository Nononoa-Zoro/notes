​	

**HTTP/2的帧结构**

![](F:\notes\image\微信截图_20200725164656.png)

前面9个字节对于每个帧都是一样的。解析的时候只需要解析这几个字节就知道期望的帧的字节大小。

帧首部字段：

**Length**：3字节，表示帧负载的长度。即Frame Payload的大小。

**Type**：1字节，当前帧的类型。

**Flags**：1字节，具体帧的类型标识。

**R**：1位，保留位。

**Stream Identifier**：31位，每个流的唯一ID。

**Frame Payload**：帧的内容，长度可变。

http2的帧类型

![](F:\notes\image\微信截图_20200725165425.png)



#### HTTP/2的流

定义：HTTP/2连接上独立的，双向的帧序列交换。

（流其实就是建立在连接上的一系列的帧）

当客户端想要发送请求的时候，会开启一个流，然后服务端在这个流上回复，类似h1，但是和h1重要的区别是因为h2有分帧，所以多个请求和响应可以相互交错。

客户端和服务端的连接建立之后，通过发送HEADERS帧启动新的流，如果首部需要更多的帧，可能还会发送COTINUATION帧（作用是扩展HEADER数据块）

**注意：HEADERS帧通过在flags字段中设置END_HEADERS标识位来标识首部的结束。**



#### HTTP/2 GET 请求

![](F:\notes\image\微信截图_20200725170834.png)

客户端发送HEADERS帧，服务端返回HEADERS帧和DATA帧。



#### HTTP/2 POST请求

![](F:\notes\image\微信截图_20200725171008.png)



#### 对比HTTP/1和HTTP/2的请求

![](F:\notes\image\微信截图_20200725172209.png)

![](F:\notes\image\微信截图_20200725172433.png)



#### 资源的优先级

客户端请求的时候首先请求重要的东西，然后以最优的顺序获取资源，从此来优化页面的性能。

客户端收到HTML之后会解析它的依赖树

![](F:\notes\image\微信截图_20200725181900.png)

h2通过流的依赖关系来解决这个问题。通过HEADERS帧和PRIORITY帧，客户端可以和服务端沟通它需要什么，以及它需要这些资源的顺寻。