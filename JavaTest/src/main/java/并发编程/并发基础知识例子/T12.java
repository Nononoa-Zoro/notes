package 并发编程.并发基础知识例子;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 有一个List，线程A向中添加10个元素，线程B监听其中元素，当list的个数为5的时候停止监听
 * <p>
 * 使用CountDownLatch实现
 * <p>
 * CountDownLatch门闩 是一个计数器
 *
 */

public class T12 {

    List list = new ArrayList();

    void add(Object o) {
        list.add(o);
    }

    int get() {
        return list.size();
    }

    public static void main(String[] args) {
        T12 t = new T12();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("thread2 启动");
            //如果size!=5 门闩等待
            if (t.get() != 5) {
                try {
                    //等着开门 整个线程在此处等待
                    countDownLatch.await();
                    //限时等待
                    //countDownLatch.await(5000,TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("thread2 结束");
        }, "thread2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("thread1 启动");

            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 add " + i);
                if (i == 5) countDownLatch.countDown();
            }
        }, "thread1").start();


    }
}
