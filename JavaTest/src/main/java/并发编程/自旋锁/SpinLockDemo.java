package 并发编程.自旋锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁底层实现是CAS 线程得不到锁的时候不会阻塞而是死循环
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {
            //未抢占成功 则一直抢占

        }
        System.out.println(Thread.currentThread().getName() + "come in ");
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "invoke myUnlock");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws Exception {
        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.myUnlock();

        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {

            lock.myLock();
            lock.myUnlock();
        }, "B").start();

    }

}
