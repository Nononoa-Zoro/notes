package 并发编程.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以实现公平锁
 *
 * 公平锁保证在多个线程抢占同一把锁的时候不会出现饥饿现象
 */
public class ReentrantLock5 implements Runnable {

    private static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁" + i);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        new Thread(new ReentrantLock5(), "thread1").start();

        new Thread(new ReentrantLock5(), "thread2").start();
    }
}
