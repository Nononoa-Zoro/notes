### redis持久化的方式

1. RDB

   SAVE和BGSAVE命令用于生成RDB文件。

   SAVE会导致redis阻塞，BGSAVE会开启一个子线程生成RDB文件。

   AOF的更新频率更高，所以在服务器启动的时候，会优先加载AOF文件。

   ##### 自动间隔保存

   用户可以设置save属性，让服务器每隔一段时间执行一次BGSAVE

   save选项

   save 900 1 // 900ms内修改了1次

   save 300 10 // 300ms内修改了10次

   save 60 10000 // 60ms内修改了10000次

   服务器维护了saveparams数组，dirty计数器（从上一次保存点到现在的数据修改次数），lastSave（上次保存点）的信息。遍历save条件只要有一个条件满足，就会执行BGSAVE生成RDB文件。

   #####RDB文件结构

   REDIS/db_version/databases/EOF/check_sum

   databases主要保存了数据库中的数据

   

2. AOF（Append Only File）

   与RDB记录数据库数据信息恢复数据库状态不同，AOF通过记录数据库的写命令来恢复数据库状态。

   ```java
   set msg "hello"
   sadd fruits "apple" "banana" "mongo"
   rpush number 1 2 3
   ```

   在上述例子中，对于RDB是记录key-value，对于AOF是记录写命令。

   ##### AOF持久化实现的方式

   1. 命令追加：服务端每执行完一个写命令之后就会把这条命令写入到aof_buf中。

   2. 文件写入与同步：AOF文件会先写入aof_buf中，通过设置的参数考虑多久同步到aof文件中。

      在每一次事件循环结束之后，redis server通过appensync的值来判断是否需要将aof_buf的值同步到aof文件中。

      appendsync的值：

      always，everysecond，no

   #####AOF重写

   是指如果一条一条记录写数据命令会导致很多命令是冗余的。所以在读取数据库最后的数据状态信息，然后将这一条命令写入AOF文件。

​      

​      

​      