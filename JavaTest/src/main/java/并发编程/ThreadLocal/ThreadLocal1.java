package 并发编程.ThreadLocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * person p在两个线程中是可见的，一个线程改了之后另外一个都可以看见
 */

public class ThreadLocal1 {

    //volatile保证可见性
    volatile static Person p = new Person();


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {

            try {
                latch.await();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"看见的"+p.name);
        }, "thread1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
            latch.countDown();
        }, "thread2").start();
    }

}

class Person {
    String name = "zhangsan";
}