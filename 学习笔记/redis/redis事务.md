### Redis事务

三个命令：MUTLI，EXEC，WATCH



MUTLI表示开启一个Redis事务，接下来的所有命令会被保存到一个FIFO的队列中。当Redis服务器收到EXEC命令的时候redis提交事务，从事务队列中取出事务执行并返回结果。



#### WATCH

是一个乐观锁，可以在EXEC命令执行之前监视任意数量的数据库键。并在EXEC命令执行的时候检查被监视的键是否至少有一个已经被修改过了。如果有的话拒绝执行事务，并向服务端返回代表事务执行失败的空回复。



#### WATCH机制是如何触发的

每个redis数据库都保存了一个watched_keys的字典，服务器可以知道哪些键正在被哪些客户端所监视。

![](D:\mdimage\微信截图_20200322203559.png)



c1,c2正在监视"name"

redis所有的数据修改操作都会遍历watched_keys，如果发现某个客户端正在监视已经修改过的key，那么redis将会发通知告诉该客户端失效。

例如：当前redis服务器修改了"name"，那么c1，c2的REDIS_DIRTY_CAS标志会被打开，此时执行EXEC会失败。