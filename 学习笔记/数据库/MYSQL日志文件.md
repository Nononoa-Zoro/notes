### MYSQL日志文件

**1.错误日志**：

​	记录以下信息：

​	mysql执行过程中的**错误信息**

​	mysql执行过程中的**告警信息**



**2.二进制日志**：

​	二进制日志是一个二进制文件，记录了对MySQL数据库**执行更改的所有操作**，并且记录了语句发生时间、执行时长、操作数据等其他额外信息，但是它不记录SELECT、SHOW等那些不改变数据库的SQL语句。二进制日志主要用于数据库**恢复**和**主从复制**，以及**审计**操作。



**3.慢查询日志**：超过一定时间的查询SQL都会记录到慢查询日志中去。（show variables like 'long_query_time'），默认10s。



**4. 查询日志（general_log）**

​	记录了数据库执行的所有指令，无论这些命令是否正确。

​	查询日志默认是没有打开的。

​	general_log_file：查询日志存放的位置

​	log_output：表示查询日志以何种方式存放。File表示存放在文件中，Table表示存放在mysql.general_log表中（慢查询在mysql.slow_log中）。None表示不记录查询日志。

![](D:\mdimage\TIM截图20200217115048.png)



### 重做日志文件（redo log file）

redo log又称重做日志文件，用于**记录事务操作的变化**，记录的是数据修改之后的值，不管事务是否提交都会记录下来。在实例和介质失败（media failure）时，redo log文件就能派上用场，如数据库掉电，InnoDB存储引擎会使用redo log恢复到掉电前的时刻，以此来保证数据的完整性。 



## redo log与binlog的区别

**第一**:redo log是在InnoDB存储引擎层产生，而binlog是MySQL数据库的上层产生的，并且二进制日志不仅仅针对INNODB存储引擎，MySQL数据库中的任何存储引擎对于数据库的更改都会产生二进制日志。

**第二**：两种日志记录的内容形式不同。MySQL的binlog是逻辑日志，其记录是对应的SQL语句。而innodb存储引擎层面的重做日志是物理日志。

**第三**：两种日志与记录写入磁盘的时间点不同，**binlog只在事务提交完成后进行一次写入**。而innodb存储引擎的**redo log在事务进行中不断地被写入**。

binlog仅在事务提交时记录，并且对于每一个事务，仅在事务提交时记录，并且对于每一个事务，仅包含对应事务的一个日志。而对于innodb存储引擎的重做日志，由于其记录是物理操作日志，因此每个事务对应多个日志条目，并且事务的重做日志写入是并发的，并非在事务提交时写入，其在文件中记录的顺序并非是事务开始的顺序。

**第四**：binlog**不是循环使用**，在写满或者重启之后，会生成新的binlog文件，redo log是**循环使用**。

**第五**：binlog可以作为恢复数据使用，主从复制搭建，redo log作为异常宕机或者介质故障后的数据恢复使用。