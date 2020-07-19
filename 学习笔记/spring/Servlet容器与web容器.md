### Servlet的由来

​	最开始，人们通常访问的是静态的网页，不需要提交什么操作。后来，为了满足一些交互需求，提出了公共网关接口（Common Gateway Interface CGI）, CGI程序以进程为单位处理客户端请求，耗时较长。![](F:\mdimage\cgi.jpg)



​	Java Servlet（Java服务器小程序）是一个基于Java技术的Web组件，运行在服务器端，它由Servlet容器所管理，用于生成动态的内容。 Servlet是平台独立的Java类，编写一个Servlet，实际上就是按照Servlet规范编写一个Java类。Servlet被编译为平台独立 的字节码，可以被动态地加载到支持Java技术的Web服务器中运行。 ![](F:\mdimage\servlet.jpg)

####  Servlet容器 

​	Servlet容器也叫做Servlet引擎，是Web服务器或应用程序服务器的一部分，用于在发送的请求和响应之上提供网络服务，解码基于 MIME的请求，格式化基于MIME的响应。Servlet没有main方法，不能独立运行，它必须被部署到Servlet容器中，由容器来实例化和调用 Servlet的方法（如doGet()和doPost()），Servlet容器在Servlet的生命周期内包容和管理Servlet。在JSP技术 推出后，管理和运行Servlet/JSP的容器也称为Web容器 .

### Tomcat 

Tomcat是一个免费的开放源代码的Servlet容器。

Tomcat服务器接受客户请求并做出响应的过程如下，与上图类似：

1）客户端（通常都是浏览器）访问Web服务器，发送HTTP请求。 
2）Web服务器接收到请求后，传递给Servlet容器。 
3）Servlet容器加载Servlet，产生Servlet实例后，向其传递表示请求和响应的对象。 
4）Servlet实例使用请求对象得到客户端的请求信息，然后进行相应的处理。 
5）Servlet实例将处理结果通过响应对象发送回客户端，容器负责确保响应正确送出，同时将控制返回给Web服务器。

![](F:\mdimage\tomcat.jpg)

- Server 元素表示整个 Catalina servlet 容器。
- Service元素表示一个或多个连接器组件的组合，这些组件共享一个用于处理传入请求的引擎组件。Server 中可以有多个 Service。
- Executor表示可以在Tomcat中的组件之间共享的线程池。
- Connector代表连接组件。Tomcat 支持三种协议：HTTP/1.1、HTTP/2.0、AJP。
- Context元素表示一个Web应用程序，它在特定的虚拟主机中运行。每个Web应用程序都基于Web应用程序存档（WAR）文件，或者包含相应的解包内容的相应目录，如Servlet规范述。
- Engine元素表示与特定的Catalina服务相关联的整个请求处理机器。它接收并处理来自一个或多个连接器的所有请求，并将完成的响应返回给连接器，以便最终传输回客户端。
- Host元素表示一个虚拟主机，它是一个服务器的网络名称（如“www.mycompany.com”）与运行Tomcat的特定服务器的关联