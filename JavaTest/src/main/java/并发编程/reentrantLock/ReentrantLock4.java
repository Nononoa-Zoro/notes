package 并发编程.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以被中断
 *
 * lock.lockInterruptibly()表示可以接受其他线程的中断请求
 *
 */
public class ReentrantLock4 {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                //表示这个锁可以响应外部中断请求
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " start!");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " end!");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted!");
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
    }

}
