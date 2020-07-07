package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;

/**
 * 如果当前线程正处在阻塞状态比如调用某个对象的Wait方法，此时如果收到了中断信号，则会抛出InterruptedException
 * <p>
 * 如果当前线程正处在运行状态，此时收到中断信号，不会做任何操作，仅仅是线程的中断标志位设置为true
 */
public class T13_Interrupt的含义 {
    private Object lock = new Object();
    private volatile int count = 2;

    public void A() {
        synchronized (lock) {
            if (count != 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            System.out.println(Thread.currentThread().getName() + "获得锁并执行");
            notifyAll();
        }
    }


    public void B() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("正常运行中");
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        T13_Interrupt的含义 t = new T13_Interrupt的含义();

//        Thread threadA = new Thread(() -> {
//            t.A();
//        }, "A");
//        threadA.start();
//        threadA.interrupt();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(threadA.isAlive());

        Thread threadB = new Thread(() -> {
            t.B();
        }, "B");
        threadB.start();
        TimeUnit.SECONDS.sleep(1);
        threadB.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadB.isAlive());

    }
}
