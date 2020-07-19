#### Synchronized底层实现原理

Java对象头主要存储的是Mark Word。Synchronized不论是修饰方法还是修饰代码块都是通过持有对象的锁来实现同步的。所有Synchronized修饰的代码块，需要进入的线程都需要获得该对象的监视器锁（Monitor）。

![](D:\mdimage\20190403174421871.jpg)



对于一个synchronized修饰的方法(代码块)来说：

当多个线程同时访问该方法，那么这些线程会先被放进_EntryList队列，此时线程处于blocking状态
当一个线程获取到了实例对象的监视器（monitor）锁，那么就可以进入running状态，执行方法，此时，ObjectMonitor对象的_owner指向当前线程，_count加1表示当前对象锁被一个线程获取
当running状态的线程调用wait()方法，那么当前线程释放monitor对象，进入waiting状态，ObjectMonitor对象的_owner变为null，_count减1，同时线程进入_WaitSet队列，直到有线程调用notify()方法唤醒该线程，则该线程重新获取monitor对象进入_Owner区
如果当前线程执行完毕，那么也释放monitor对象，进入waiting状态，ObjectMonitor对象的_owner变为null，_count减1
