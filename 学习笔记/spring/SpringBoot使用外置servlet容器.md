### 外置的Servlet容器：外面安装Tomcat---使用WAR包的方式打包

​	JAR是JAVAclass文件的压缩包，WAR是一个web程序进行打包便于部署的压缩包，里面包含我们web程序需要的一些东西，其中包括web.xml的配置文件，前端的页面文件，以及依赖的JAR。 

### 步骤

1）必须创建一个war项目；

2）将嵌入式的Tomcat指定为provided

```java
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
```

3）编写一个SpringBootServletInitializer的子类

```java
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBoot04WebJspApplication.class);
    }

}

```

4）启动服务器