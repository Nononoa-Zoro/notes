minikube start

kubectl get nodes //创建一个单节点的集群

kubectl cluster-info  //查看集群信息

kubectl run kubernetes-bootcamp --image=docker.io/jocatalin/kubernetes-bootcamp:v1 --port=8080  //部署应用

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