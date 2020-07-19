**角色**

1.leader

2.follower

3.candidate



**初始时**，所有人都是follower，timeout结束有一个人成为candidate开始选举。

自己投自己一票，如果获得大多数投票则candidate成为leader。



选举出leader之后，client所有对集群节点的操作都必须通过leader。

比如，client提出请求set 5。这个时候会去找leader，leader会发送请求给follower，此时leader尚未执行改请求，只有多数follower反馈给leader说同意set 5之后，leader才会开始执行该请求。



**领导人选举**：

​	follower在一个timeout之后开始成为candidate。然后candidate会开始向其他的follower征询意见（也就是询问是否支持自己成为leader）。如果一旦票数占大多数，candidate就成为leader。

​	在成为leader之后，leader节点会定时向其他节点发送**心跳包**，以告诉其他节点leader节点的存在（其他follower都老实点，不要想当leader）。同时，**心跳包**携带着客户端需要该集群节点修改的数据信息。

​	如果当前leader节点挂掉，剩下的follower就开始进入timeout，谁的timeout先结束谁就会成为下一任leader。



**如果同时有两个（或者多个follower的timeout结束）怎么选举？**

这种情况下将执行下一轮的选举，所有的节点进入timeout。



**脑裂（出现网络分区怎么实现数据同步）**

![](F:\mdimage\微信截图_20191030134917.png)

​	上图中，节点C D E和节点A B处于不同的网络分区中，各自都有自己的leader。上面的 leader时E，下面的leader是B。数据只有在各自的网络分区中可以保持一致性，当网络分区消失时，B会向其余4个节点发送心跳包，E也会向其余4个节点发送心跳包，但是B会检测到E的term（也就是任期）是最大的，所以现在这个集群中的leader为E。所以所有的节点都会听E的。

因为之前A,B节点是少数派，就算有请求到B，节点B也不会将数据写入，它只会记录到日志中。当网络分区消失时，（也就是整个网络连通时）A,B回滚，然后得到节点E传输的数据并提交。







