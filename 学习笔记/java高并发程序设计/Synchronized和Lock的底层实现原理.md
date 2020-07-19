### Synchronized

![](D:\mdimage\微信截图_20200312103019.png)

反编译的结果

![](D:\mdimage\微信截图_20200312103123.png)



![](D:\mdimage\微信截图_20200312103245.png)





Synchronized修饰方法和修饰代码块的处理方式不同。

对于同步方法，JVM采用`ACC_SYNCHRONIZED`标记符（常量池中）来实现同步。 对于同步代码块。JVM采用`monitorenter`、`monitorexit`两个指令来实现同步。 

可以把执行`monitorenter`指令理解为加锁，执行`monitorexit`理解为释放锁。 每个对象维护着一个记录着被锁次数的计数器。未被锁定的对象的该计数器为0，当一个线程获得锁（执行`monitorenter`）后，该计数器自增变为 1 ，当同一个线程再次获得该对象的锁的时候，计数器再次自增。当同一个线程释放锁（执行`monitorexit`指令）的时候，计数器再自减。当计数器为0的时候。锁将被释放，其他线程便可以获得锁。 

无论是`ACC_SYNCHRONIZED`还是`monitorenter`、`monitorexit`都是基于Monitor实现的，在Java虚拟机(HotSpot)中，Monitor是基于C++实现的，由ObjectMonitor实现。 



Synchronized在JDK1.6之前是一个重量级锁，会触发系统调用，将用户态转化为核心态。



### Monitor(监视器)

每一个Java对象关联一个监视器，被Synchronized修饰的方法或者代码块只有获取了对象的监视器才可以进入同步代码块。



管程 (英语：Monitors，也称为监视器) 是一种程序结构，结构内的多个子程序（对象或模块）形成的多个工作线程互斥访问共享资源。这些共享资源一般是硬件设备或一群变量。管程实现了在一个时间点，最多只有一个线程在执行管程的某个子程序。与那些通过修改数据结构实现互斥访问的并发程序设计相比，管程实现很大程度上简化了程序设计。 管程提供了一种机制，线程可以临时放弃互斥访问，等待某些条件得到满足后，重新获得执行权恢复它的互斥访问。 

Java提供了类似操作系统管程的实现------Monitor。

Java中Monitor的实现：ObjectMonitor（由C++实现）

![](D:\mdimage\微信截图_20200312105550.png)



_owner：指向持有ObjectMonitor对象的线程

_WaitSet：存放处于wait状态的线程队列

_EntryList：存放处于等待锁block状态的线程队列

_recursions：锁的重入次数

_count：用来记录该线程获取锁的次数





### ReentrantLock



reentrantLock.lock()======>sync.lock()

![](D:\mdimage\微信截图_20200312114750.png)





![](D:\mdimage\微信截图_20200312115206.png)



**公平锁的lock方法的实现**

![](D:\mdimage\微信截图_20200312115513.png)

**acquire传递的参数是1**

![](D:\mdimage\微信截图_20200312120025.png)

![](D:\mdimage\微信截图_20200312120057.png)

​	![](D:\mdimage\微信截图_20200312120415.png)

### 总结

lock的存储结构：一个int类型状态值state（用于锁的状态变更），一个双向链表（用于存储等待中的线程） 。

lock获取锁的过程：本质上是通过CAS来获取状态值修改，如果当场没获取到，会将该线程放在线程等待链表中。 

lock释放锁的过程：修改状态值，调整等待链表。 

![](D:\mdimage\11772136-630de1626ef407e1.webp)

