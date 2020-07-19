**查询状态**

命令：show full processlist

用途：查看mysql连接（每一个线程）的状态。

Sleep（等待客户端请求），Query（执行查询），Locked（等待锁），Analysis and Statistics（生成查询计划） ，Copying to tmp [on disk]（复制临时表） , Sorting result （对结果进行排序）, Sending data（传送数据）。



**查询优化器**

mysql使用基于成本的优化器，成本的最小单位是读取一个4K大小的数据页的成本。

命令：show Status like 'Last_query_cost'; 可以查看上一次查询的查询成本。

mysql优化器可能会选择错误的执行计划：

mysql不知道哪些页面在内存中，哪些在磁盘上。



**优化COUNT,MIN,MAX**

COUNT:在MYISAM中维护了一个变量来存放数据表的行数。如果没有任何查询条件（where）的COUNT(*)查询可以得到存储引擎的优化。

MIN，直接获取索引的第一行。MAX直接获取索引的最后一行记录。



**count(*)还是count(列)**

count(*)会统计包含null的数据行，count(列)不会统计包含null的数据行。



**子查询IN和连接查询join的区别**

```sql
#in 会自动去重 （如果t表中的id在t1表中对应有多个重复的id那么只会返回一个id）
SELECT * FROM t WHERE t.id in (SELECT t1.tid FROM t1 );

#join 不会去重 （如果t表中的id在t1表中对应了多个，那么这多个id都会返回）
SELECT t.id FROM t JOIN t1 on t.id=t1.tid;
```



**limit查询优化（经常在order by 语句后面使用 order by limit 50,5）**

1.使用有索引的列或是主键列来进行order by。这样可以利用索引的顺序进行排序。

2.不要一直从第一个位置使用limit，这样会导致扫描大量的数据。每次应该从需要的位置开始查找，所以程序应该记录主键值，用于下一次的limit。



