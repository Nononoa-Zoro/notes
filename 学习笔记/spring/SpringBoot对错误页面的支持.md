##### SpringBoot对错误页面的支持

1.如果有模板引擎，springboot会在template/error中去找4xx.html(客户端错误)和5xx.html(服务器错误)。如果有精确指定的错误类型页面如404.html那么如果发生404错误就会去找404.html。

##### SpringBoot做错误自适应

1. 创建自己的RuntimeException

   ```java
   public class UserNotExistException extends RuntimeException{
       public UserNotExistException() {
           super("用户不存在");
       }
   }
   ```

   

2. 创建自己的错误处理器

   ```java
   @ControllerAdvice
   public class MyExceptionHandler {
       //出现错误之后转发到/error进行自适应处理 但是这个时候还是采用的默认的界面
       @ExceptionHandler(UserNotExistException.class)
       public String handleException(Exception e,HttpServletRequest request){
           Map<String,Object> map= new HashMap<>();
           //必须传入自己的状态码4xx 5xx 才可以做web和客户端的自适应
           request.setAttribute("javax.servlet.error.status_code",400);
           return "forward:/error";
       }
   }
   ```

3. 创建自己的返回值

   ```java
   @Component
   public class MyErrorAttributes extends DefaultErrorAttributes {
       @Override
       public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
           Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
           map.put("company","atguigu");
           map.put("coding","zenghao");
           return map;
       }
   }
   ```

4. 在resource/templates/error文件夹中创建4xx.html 5xx.html。此时在页面中可以拿到之前定义的值。而且在客户端访问只会返回json数据。

   ## SpringBoot默认使用的是嵌入式的servlet容器

   问题

   1）如何定制和修改Servlet容器的相关配置？(ServerProperties)

   方法一

   ```java
   #设置访问端口
   server.port=8081
   #设置项目的访问路径
   server.servlet.context-path=/crud
   
   //通用的Servlet配置
   server.xxx
   //Tomcat的配置
   server.tomcat.xxx
   ```

   方法二：编写一个ConfigurableServletWebServerFactory：嵌入式的Servlet容器定制器来修改Servlet容器的配置

   ```java
   @Bean
   public ConfigurableServletWebServerFactory configurableServletWebServerFactory(){
    	TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8585);
        return factory;
   }
   ```

   

   

   2）SpringBoot可以支持其他的Servlet容器吗？

   ```java
   		@Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
   		@ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
   		public DispatcherServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet) {
   			DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet,
   					this.webMvcProperties.getServlet().getPath());
               //getPath 默认拦截所有的“/” 请求 除了jsp请求 
               //可以通过修改server.servletPath来修改springMvc的前端控制器默认拦截的请求路径
   			registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
   			registration.setLoadOnStartup(this.webMvcProperties.getServlet().getLoadOnStartup());
   			if (this.multipartConfig != null) {
   				registration.setMultipartConfig(this.multipartConfig);
   			}
   			return registration;
   		}
   
   ```

   

   