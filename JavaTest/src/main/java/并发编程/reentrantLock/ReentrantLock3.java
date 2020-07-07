package 并发编程.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized对比ReentrantLock
 * <p>
 * ReentrantLock可以尝试获取锁tryLock
 */
public class ReentrantLock3 {

    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("thread1 " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
       /*boolean getLock = lock.tryLock();
        System.out.println("m2 locked");
        if(getLock)lock.unlock();*/

        boolean getLock = false;
        try {
            //tryLock 还可以指定尝试获取锁的时间 如果超过这个时间就不再等待锁
            getLock = lock.tryLock(5, TimeUnit.SECONDS);
            if(getLock){
                System.out.println("m2 locked");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (getLock) lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantLock3 r = new ReentrantLock3();

        new Thread(r::m1, "thread1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r::m2, "thread2").start();
    }
}
