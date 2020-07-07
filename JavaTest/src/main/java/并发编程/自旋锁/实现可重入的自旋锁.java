package 并发编程.自旋锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class 实现可重入的自旋锁 {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    private int count;

    public void lock() {
        Thread thread = Thread.currentThread();
        //获取到了 说明之前该线程加过锁
        if (thread == atomicReference.get()) {
            count++;
            return;
        }
        //第一次获得这个锁
        while (!atomicReference.compareAndSet(null, thread)) {
            //循环等待
        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        //拿得到说明之前加过锁
        if (thread == atomicReference.get()) {
            //判断加了多少次
            if (count > 0) {
                count--;
            } else {
                atomicReference.compareAndSet(thread, null);
            }
        }
    }


    public static void main(String[] args) {
        实现可重入的自旋锁 r = new 实现可重入的自旋锁();

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            r.lock();
            System.out.println(name + "第一次获取到锁");
            r.lock();
            System.out.println(name + "第二次获取到锁");
            r.unLock();
            System.out.println(name + "释放锁1");
            r.unLock();
            System.out.println(name + "释放锁2");
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            r.lock();
            System.out.println(name + "第一次获取到锁");
            r.lock();
            System.out.println(name + "第二次获取到锁");
            r.unLock();
            System.out.println(name + "释放锁1");
            r.unLock();
            System.out.println(name + "释放锁2");
        }, "B").start();


    }

}
