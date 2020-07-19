### 301 Moved Permanently

服务器知道用户请求的是什么资源，但是该资源的url永久变更，希望下次使用新的url访问该资源。

### 302 Moved Temporarily   

临时重定向，表示用户请求的资源改变，返回http消息头包含location字段。

### 304 Not Modified

自上次请求到现在，所请求资源未修改

### 401 Unauthorized 未认证

表示认证错误，请求没有被认证或者认证不准确。http头部没有authorization字段。

### 403 Forbidden 未授权

表示服务器完成认证过程，但是客户端没有权限访问所需要的资源。

### 502 Bad GateWay

网关错误，后台的服务器没有起来

### 504 GateWay TimeOut

nginx配置出错，找不到服务器



