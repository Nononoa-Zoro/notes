package 并发编程.并发基础知识例子;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 有一个List，线程A向中添加10个元素，线程B监听其中元素，当list的个数为5的时候停止监听
 * <p>
 * 使用wait/notify实现
 * wait会释放锁 notify不会释放锁
 */

public class T11 {

    List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int get() {
        return list.size();
    }

    public static void main(String[] args) {
        T11 t = new T11();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("thread2 启动");
                if (t.get() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread2 结束");
                lock.notifyAll();
            }
        }, "thread2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("thread1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    t.add(new Object());
                    System.out.println("thread1 add " + i);
                    if (i == 5) {
                        lock.notifyAll();
                        //释放锁 让thread1得以执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread1").start();


    }
}
