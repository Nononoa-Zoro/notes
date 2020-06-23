#### gRpc常见命令

1.查询服务列表

grpcurl -plaintext localhost:80 list

```go
qyaccess_token.AccessTokenSvc
qyaccess_token.GardenerAccessTokenSvc
qywx_app_manager.AppManagerService	
```



2.查询某个服务下具体有哪些方法

grpcurl -plaintext localhost:80 list qyaccess_token.GardenerAccessTokenSvc



3.查看某个方法的详细描述信息

grpcurl -plaintext localhost:80 describe qyaccess_token.GardenerAccessTokenSvc



4.调用某个服务的方法

grpcurl -plaintext -d '{"value":"123"}' localhost:80 qyaccess_token.GardenerAccessTokenSvc.SendTmpAuthCode

