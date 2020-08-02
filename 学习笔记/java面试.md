### volatile关键字的作用

1. 保证可见性
2. 不保证原子性（Atomic类可以实现原子性）
3. 禁止指令重排序



### CAS原理

CAS(Compare And Swap)：比较并交换。底层实现使用Unsafe类。

当工作线程要修改一个数据的时候，会比较主内存的数据是否是原来的数据，如果是，说明没有在此之前没有其他线程修改过主存中的数据，因此可以修改该数据。



### CAS和Synchronized的区别

```java
public final int getAndIncrement(){
    return Unsafe.getAddInt(this,valueOffset,1);
}

//unsafe.getAndAddInt
//var2 是对象var1的内存偏移地址
public final int getAndAddInt(Object var1,long var2,int var4){
    int var5;
    do{
        var5=this.getIntVolatile(var1,var2);
    }while(!this.compareAndSwapInt(var1,var2,var5,var5+var4));
    return var5;
}
```

CAS不加锁，提高并发度。

Synchronized加锁实现，并发度低。



#### CAS的缺点

1. CAS使用的是自旋锁，也就是当设置值不成功的时候会一直循环，CPU开销大。
2. CAS只能保证一个共享变量的原子操作，对于多个共享变量的原子操作只能通过加锁的方式来实现。
3. CAS会有ABA问题，即一个线程在更改了主存中的值之后又将主存的值改回去，此时另外一个线程看到的仍然是没有改变的数据，所以compareAndSet执行成功。可以用时间戳解决这个问题。



#### 公平锁

多个线程按照申请锁的先后顺序，依次获得锁。



#### 非公平锁

不是按照线程申请锁的先后顺序获得锁，存在抢占的情况，可能会导致饥饿现象。



#### 可重入锁

Synchronized和ReentrantLock都是可重入锁。

可重入锁是指，当一个线程获得了锁以后，它可以进入任何一个需要锁的同步代码块，即同步方法可以访问同步方法。（也就是说一个锁可以获得多次，在锁上面有一个计数器，表示当前线程获得了几次这个锁）



#### 自旋锁

尝试获取锁失败的线程不会立即阻塞，而是采用循环的方式不断地尝试获取锁。这样做的好处是减少上下文的切换，缺点是占用CPU资源。 





#### 读写锁

```java
ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

rwLock.writeLock().lock();
rwLock.writeLock().unlock();

rwLock.readLock().lock();
rwLock.readLock().unlock();
```

读读不加锁，读写，写写都加锁。

除了读读线程不加锁，其他情况都会加锁。



### 进程和线程的区别

1. 进程是操作系统资源分配的基本单位，线程是cpu进行任务调度和执行的基本单位。
2. 每个进程都有自己独立的数据空间，进程切换的开销大；多个线程共享进程的数据空间，线程切换开销小。
3. 线程之间的通信比进程之间的通信更加方便。因为线程共享进程的数据空间。



#### 协程

协程是属于线程的。协程是在线程里面跑的，所以协程又叫微线程。

协程没有线程的上下文消耗，协程的调度和切换都是程序员手动切换的。



### final关键字

final修饰基本数据类型的时候，该数值在赋值完成之后就不会改变这个值。

final修饰引用类型的时候，则对其初始化之后不能指向另外一个对象。

final修饰一个类的时候，表示这个类不能被继承。

final修饰一个方法的时候，子类不能修改这个方法。



### Lock和Synchronized的区别

Synchronized是Java的一个关键字，用来修饰一个方法或者一个代码块的时候，只允许一个线程进入该代码块。

1. Synchronized会自动释放锁

   Lock是一个接口，Lock不会自动释放锁，一般需要在finally代码块中显式释放锁

2. Lock可以响应中断

```java
lock.lockInterruptibly（）//当主线程发出中断信号的时候，当前线程就会释放锁
```

3. Lock可以实现限时等待，Synchronized如果没有获取到锁就会一直等待。

```java
   boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
   //指定等待该锁的时间
```

4.  Lock是一个接口，有很多的实现类，ReentrantLock,ReadLock,WriteLock,还可以通过构造函数指定生成一个公平锁。



### Synchronized修饰Static和修饰成员方法分别锁定的是什么？

修饰Static方法，锁定的是类的class文件，也就是说，就算有多个对象的实例方法，一个线程也只能访问其中的一个。

修饰普通成员方法锁定的是堆上的对象实例，也就是说，对于同一个对象实例的方法，同一时间只能有一个线程访问，但是如果有多个对象实例，多个线程可以访问不同的对象实例的方法。



### 方法重载和方法覆盖

Java中方法的重载发生在同一个类中的两个方法，他们的方法名称相同但是方法的参数，类型，顺序不同。如果两个方法只有返回值不同不能叫做方法的重载。

方法覆盖发生在子类和父类中，子类可以通过重写父类的同名方法，来修改父类的默认实现，当然子类还是可以通过super关键字访问父类的实现。



### 抽象类和接口的区别

1. 接口只能定义抽象方法，抽象类除了定义抽象方法还可以实现方法。
2. 接口可以实现多个，抽象类只能继承一个。
3. 接口中的变量都是public static final  修饰的，方法都是public abstract （java8引入了default关键字可以默认实现）



### Java8为什么会引入接口的默认实现？

如果JDK以后的版本希望在接口中添加一个方法，那么所以的实现类都需要改变，所以java8允许接口有默认的实现方法。

主要原因是Collection类需要转化为stream。如果不在Collection接口中提供一个默认的Stream的实现的话，所以的Collection的子类都要实现这个方法。