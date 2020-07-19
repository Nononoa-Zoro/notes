### INNODB && MYISAM

1. INNODB支持事务，MYISAM不支持事务。
2. INNODB默认支持行级锁，MYISAM支持表级锁。
3. INNODB支持MVCC（多版本并发控制，非锁定一致性读取），MYISAM不支持。
4. INNODB支持外键，MYISAM不支持。
5. INNODB不支持全文索引，MYISAM支持。
6. INNODB使用聚簇索引，数据和索引存储在一个文件中。主键索引的叶子节点存放的就是数据。MYISAM采用非聚簇索引，数据和索引文件分开存放。
7. INNODB不保存具体的行数，MYISAM使用一个变量保存具体的行数。
8. MYISAM支持前缀压缩索引。减少索引字段的长度。

Innodb默认使用行锁，锁粒度小，所以可以支持高并发。其设计主要面向OLTP应用。读少写多的场景。

MyISAM使用表锁，假如只是更新一个字段也需要锁住整张表。并发度低。主要用于查询较多的应用环境，面向OLAP。