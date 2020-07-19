### CountDownLatch

倒计时器，接受一个整数作为构造函数，表示需要等待的线程的个数。等到这些线程执行完成之后，主线程才可以开始执行。

```java
public CountDownLatch(int count)
countDownLatch.countDown();//表示该线程执行结束 计数器要减一
countDownLatch.await()//表示当前线程等待计数器计数完成
```

### CyclicBarrier

循环栅栏，它可以循环使用，完成一次计数之后还可以执行对应的操作。比如等10个线程执行结束之后，计数器归零，然后重新开始计数。

```java
public CyclicBarrier(int parties,Runnable barrierAction)//parties表示计数个数 barrierAction表示所有等待的线程执行完之后会执行的操作
```

