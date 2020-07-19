### JDBC

```java
 <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
 </dependency>
 <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
  </dependency>
```

添加jdbc启动依赖和mysql连接器（driver）

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
```

