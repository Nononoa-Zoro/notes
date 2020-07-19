### OAUTH

**OAuth 就是一种授权机制。数据的所有者（用户）告诉系统（资源服务器GitHub），同意授权第三方应用进入系统，获取这些数据。系统从而产生一个短期的进入令牌（token），用来代替密码，供第三方应用使用。**



#### 令牌和密码

令牌（token）与密码（password）的作用是一样的，都可以进入系统，但是有三点差异。

（1）令牌是短期的，到期会自动失效，用户自己无法修改。密码一般长期有效，用户不修改，就不会发生变化。

（2）令牌可以被数据所有者撤销，会立即失效。

（3）令牌有权限范围（scope），比如只能进小区的二号门。对于网络服务来说，只读令牌就比读写令牌更安全。密码一般是完整权限。



### OAUTH规定了四种方式获得令牌

- 授权码（authorization-code）
- 隐藏式（implicit）
- 密码式（password）：
- 客户端凭证（client credentials）

注意，不管哪一种授权方式，第三方应用申请令牌之前，都必须先到系统备案，说明自己的身份，然后会拿到两个身份识别码：客户端 ID（client ID）和客户端密钥（client secret）。这是为了防止令牌被滥用，没有备案过的第三方应用，是不会拿到令牌的。 



GitHub上注册第三方服务器信息，生成一个Client ID和一个Client Secret。



![](D:\mdimage\TIM截图20200316104358.png)



### 授权码方式

1. 客户访问第三方服务器A，此时发送请求到资源服务器B。

   ```java
   https://b.com/oauth/authorize?
     response_type=code&
     client_id=CLIENT_ID&
     redirect_uri=CALLBACK_URL&
     scope=read
     //response_type=code表示采用的类型是授权码，需要资源服务器返回一个code
     //client_id是第三方服务器在资源服务器上注册的id
     //redirect_url是在资源服务器接收或拒绝请求以后的后调地址
     //scope表示第三方服务器请求的资源范围
   ```

2. 资源服务器此时要求用户登录，询问用户是否授权第三方服务器访问以下资源。用户输入密码登录以后，资源服务器按照指定的redirect_uri进行跳转，并返回第三方服务器一个授权码。

   ```java
   https://a.com/callback?code=AUTHORIZATION_CODE
   ```

3. 第三方服务器在拿到授权码以后就可以像资源服务器申请令牌。

   ```java
   https://b.com/oauth/token?
    client_id=CLIENT_ID&
    client_secret=CLIENT_SECRET&
    grant_type=authorization_code&
    code=AUTHORIZATION_CODE&
    redirect_uri=CALLBACK_URL
    //redirect_uri资源服务器返回令牌以后的回调地址
   ```

4. 资源服务器收到请求之后，向第三方服务器发送令牌，它是一段JSON数据。

   ```java
   {    
     "access_token":"ACCESS_TOKEN",
     "token_type":"bearer",
     "expires_in":2592000,
     "refresh_token":"REFRESH_TOKEN",
     "scope":"read",
     "uid":100101,
     "info":{...}
   }
   ```

5. 第三方服务器通过token访问资源服务器对应的资源。具体做法是在http请求头加上一个Authorization字段。



















