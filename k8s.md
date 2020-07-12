kubectl cluster-info  //查看集群信息



kubectl run kubernetes-bootcamp --image=docker.io/jocatalin/kubernetes-bootcamp:v1 --port=8080  

//部署应用 创建一个应用（应用名称为kubernetes-bootcamp，使用的镜像是docker.io/jocatalin/kubernetes-bootcamp:v1，应用运行的端口是8080）



通过service暴露容器的端口，这样会随机将一个外部端口绑定到pod内部

kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080 //暴露pod的内部端口

```go
$ kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080
service/kubernetes-bootcamp exposed
```



kubectl get services //可以查看应用被映射到哪一个端口了

```go
$ kubectl get services
NAME                  TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
kubernetes            ClusterIP   10.96.0.1        <none>        443/TCP          3m41s
kubernetes-bootcamp   NodePort    10.108.169.135   <none>        8080:30519/TCP   68s
```

pod内部端口8080被映射到外部的30519端口，外部可以通过30519端口访问Pod。



**Pod扩缩容**

kubectl get deployments //查看副本的数量

```go
$ kubectl get deployments
NAME                  READY   UP-TO-DATE   AVAILABLE   AGE
kubernetes-bootcamp   1/1     1            1           8m58s
```

kubectl scale deployments/kubernetes-bootcamp --replicas=3 //增加副本的数量到3个

```go
$ kubectl scale deployments/kubernetes-bootcamp --replicas=3
deployment.apps/kubernetes-bootcamp scaled
```

kubectl scale deployments/kubernetes-bootcamp --replicas=2 //减少副本的数量到2个

kubectl get pods//可以查看扩缩容pod的状态

**升级镜像的版本**

kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2 

**回退到上一个版本**

kubectl  rollout undo deployments/kubernetes-bootcamp 



#### 几个重要的基本概念

##### Cluster

是计算，存储和网络资源的集合，k8s利用这些资源来运行容器中的应用。

##### Master

是Cluster的大脑，主要负责调度，决定应用放在哪里运行。

##### Node

职责是运行容器应用。Node由Master管理，Node负责监控并汇报容器的状态。

##### Pod

是k8s最小的工作单元，每个Pod包含一个或多个容器。Pod中的容器会作为一个整体被调度到一个Node中运行。通常会将紧密相关的容器放到一个Pod中，同一个Pod中所有的容器共享IP和端口。Pod是k8s调度的最小单位，同一Pod中的容器始终一起被调度。

```go
kubectl get pods //查看当前的Pod
```

##### Controller

k8s不会直接创建Pod，而是通过Controller来管理Pod的。Controller定义了Pod的部署特性，比如有几个副本，在什么样的Node中运行等。下面是常见的Controller的类型：

​	**Deployment**：是最常用的Controller，它可以管理多个Pod副本，并确保Pod按照期望的状态运行。

​    **ReplicaSet**：实现了Pod的多副本管理。

​    **DaemonSet**：每个Node最多运行一个Pod的场景

​    **StatefuleSet**：保证Pod的每个副本在整个的生命周期中的名称是不变的

​	 **Job**：用于结束时就删除的应用

##### Service

Deployment可以部署多个副本，每个Pod都有自己的IP。每次Pod销毁重建IP都会变化。k8s访问容器由Service完成。

Service有自己的IP和端口，Service为Pod提供负载均衡。

##### NameSpace

多个用户或者多个项目组使用一个k8s Cluster，使用NameSpace来将Controller和Pod区分开来。



#### Deployment

**运行deployment**

kubectl run nginx-deployment --image=nginx:1.7.9 --replicas=2 

创建包含两个副本的Deployment名称为nginx-deployment，容器镜像为nginx:1.7.9

**查看deplyment的状态**

kubectl get deployment

**删除deployment**

kubectl delete deployment <deployment_name>



### 部署k8s 集群

#### 安装kubelet  kubeadm kubectl 

**kubelet**运行在所有节点上，负责启动Pod和容器。

**kubeadm**用于初始化Cluster

```go
kubeadm init --apiserver-advertise-address 192.168.56.105 --pod-network-cidr=10.244.0.0/16
//--apiserver-advertise-address表示master使用哪一个interface与其他的Cluster节点通信
//--pod-network-cidr指定Pod网络的范围
```

kubeadm会生成token和证书，生成KubeConfig文件，kubelet需要用这个文件与Master通信。



**kubectl**是k8s的命令行工具。通过kubectl可以部署管理应用，查看各种资源，创建，删除和更新各种组件。

```go
kubeadm join --token <kubeadm初始化cluster生成的token> 192.168.56.105:6443
//在node-1上执行可以将当前节点注册到Cluster中

kubeadm token list
//可以查看kubeadm生成的token 

当kubeadm join命令执行成功之后，通过kubectl get nodes可以查看节点状态
```





### k8s的架构

##### Master节点

**API Server**：提供RESTFUL的HTTP,HTTPS API

**Scheduler**：决定将Pod放在哪个Node上运行

**Controller Manager**：由多个Controller组成，负责管理集群的资源

**Etcd**：负责保存集群的配置信息，当数据变更时会及时通知k8s的相关组件

**Pod**：要使Pod可以相互通信，k8s Cluster必须部署Pod网络。



##### Node节点

Node是运行Pod的地方

**kubelet**：是Node的agent，当scheduler确定在某个Node上运行Pod之后会将，具体的配置信息发送给该节点的kubelet。kubelet根据这些信息创建和运行容器。

**kube-proxy**：负责将service的TCP/UDP数据流转发到后端容器，如果有多个副本，kube-proxy会实现负载均衡。

**Pod网络**：实现Pod之间的网络互通



#### Controller

k8s使用多种类型的controller管理Pod的生命周期。为了满足不同的业务场景，k8s开发了Deployment,ReplicaSet,DaemonSet,StatefulSet,Job等多种Controller。

kubectl run nginx-deployment --image=nginx:1.7.9 --replicas=2

创建一个包含两个副本的Deployment，名字叫做nginx-deployment

**kubectl get deployment nginx-deployment**：查看nginx-deployment的状态

**kubectl describe deployment**：查看deployment的详细信息





#### Node可以有label

label是为了给Node打标签，有时候可能会存在这样一种需求，将一个pod调度到特定的Node中去。

比如：

kubectl label node k8s-node1 disktype=ssd，label是key-value对。给k8s-node1节点贴上disktype=ssd的标签。



使用kubectl get node --show-labels查看节点的label。

