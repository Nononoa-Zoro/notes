### Docker的核心概念

docker主机(host)：安装了Docker程序的机器（Docker直接安装在操作系统之上）

docker客户端（client）：连接docker进行操作

docker仓库（Registry）：用来保存好软件打包好的镜像

docker镜像（images）：软件打包好的镜像

docker容器（Container）：镜像启动之后就是一个容器

![](F:\mdimage\docker1.png)

使用Docker的步骤：

1）安装Docker

2）去Docker仓库找到需要安装软件的对应镜像

3）使用Docker运行这个镜像，这个镜像就会生成一个Docker容器

### Docker常见命令

1.启动Docker，systemctl start docker

2.停止Docker，systemctl stop docker

3.搜索镜像 docker search mysql(镜像名字)

4.拉取镜像 docker pull mysql(镜像名字):5.5.59(版本号)

5.查看当前docker中有多少镜像 docker images

6.删除镜像 docker rmi (image id)

软件镜像---运行镜像---产生一个容器（正在运行的软件）

7.运行容器 docker run --name mytomcat(自己取的名字) -d tomcat:latest

8.docker ps (ps -a 可以查看所有的容器包含退出的容器)查看运行中的容器

9.停止运行中的容器 docker stop mytomcat(或者传入id)

10.docker rm (容器id) 删除指定容器

11.启动容器 docker start 容器id

12.端口映射 docker run -d -p 8888:8080 tomcat

-d : 表示后台运行

-p : 表示将主机端口映射 到容器内部的端口

service firewalld status 查看防火墙状态

service firewalld stop 关闭防火墙

13.docker logs 查看容器日志

```shell
docker run --name mysql01 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
```





