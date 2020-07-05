minikube start



kubectl get nodes //创建一个单节点的集群



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



kubectl get deployments //查看副本的数量

```go
$ kubectl get deployments
NAME                  READY   UP-TO-DATE   AVAILABLE   AGE
kubernetes-bootcamp   1/1     1            1           8m58s
```



kubectl scale deployments/kubernetes-bootcamp --replicas=3 //增加副本的数量

```go
$ kubectl scale deployments/kubernetes-bootcamp --replicas=3
deployment.apps/kubernetes-bootcamp scaled
```



kubectl scale deployments/kubernetes-bootcamp --replicas=2 //减少副本的数量到2个



kubectl set image deployments/kubernetes-bootcamp kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2 //升级镜像的版本



kubectl  rollout undo deployments/kubernetes-bootcamp //回退到上一个版本



#### 几个重要的基本概念

##### Cluster

是计算，存储和网络资源的集合，k8s利用这些资源来运行容器中的应用。

##### Master

是Cluster的大脑，主要负责调度，决定应用放在哪里运行。

##### Node

职责是运行容器应用。Node由Master管理，Node负责监控并汇报容器的状态。

##### Pod

是k8s最小的工作单元，每个Pod包含一个或多个容器。Pod中的容器会作为一个整体被调度到一个Node中运行。

##### Controller

k8s不会直接创建Pod，而是通过Controller来管理Pod的。Controller定义了Pod的部署特性，比如有几个副本，在什么样的Node中运行等。

**Deployment**：是最常用的Controller，它可以管理多个Pod副本，并确保Pod按照期望的状态运行。

 	**ReplicaSet**：实现了Pod的多副本管理。

​	 **DaemonSet**：每个Node最多运行一个Pod的场景

​	 **StatefuleSet**：保证Pod的每个副本在整个的生命周期中的名称是不变的

​	 **Job**：用于结束时就删除的应用

##### Service

Deployment可以部署多个副本，每个Pod都有自己的IP。每次Podx销毁重建IP都会变化。k8s访问容器由Service完成。

Service有自己的IP和端口，Service为Pod提供负载均衡。

##### NameSpace

多个用户或者多个项目组使用一个k8s Cluster，使用NameSpace来将Controller和Pod



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

**kubectl**是k8s的命令行工具。通过kubectl可以部署管理应用，查看各种资源，创建，删除和更新各种组件。