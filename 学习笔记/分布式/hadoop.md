**Hadoop**

主要解决大数据的存储和计算。

特点：

1. 高可靠：主备节点切换，保证数据不丢失。
2. 高扩展：集群间分配节点，方便扩展数千的节点。
3. 高效性：在MapReduce的支持下，可以实现并行goon工作。
4. 高容错：将失败的任务自动分配。

Hadoop1.x和2.x的区别

Hadoop2.x将资源调度交给了Yarn。



### 从三个方面认识Hadoop

第一，Hadoop可以划分为Master和Slave。

第二，从HDFS角度，Hadoop可以划分为NameNode和DataNode。

第三，从MapReduce角度，Hadoop可以划分为JobTracker和TaskTracker。

