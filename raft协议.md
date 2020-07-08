#### 分布式节点的状态

Follower：跟随者。所有节点一开始都是Follower。

Candidator：候选人。如果没收到Leader的命令，那么可以成为一个候选人。

Leader：领导者。候选人给其他节点发请求，获得大多数投票之后成为领导者。



**系统所有的更改都要经过Leader**



#### Leader Election

election timeout是指followers跟随者在成为candidates候选者之前所需要等待的时间。

所有的节点初始的时候都是follower状态，此时他们会等待一个随机的election timeout，最先完成的follower就会成为candidator。然后candidator开始向其他节点发送request，请求投票。如果节点在当前term里还没有投票，就会把票给候选人。并且节点会重置election timeout时间。一旦candidator获得了多数投票，他就会成为Leader。然后Leader开始向Follower发送消息。这些消息以heartbeat timeout心跳超时指定的时间间隔发送。跟随者然后响应每个*Append Entries* 追加条目消息。此选举任期将持续到追随者停止接收心跳并成为候选人为止。



#### 日志复制
当选出一位领导人后，我们需要将系统的所有更改复制到所有节点。

