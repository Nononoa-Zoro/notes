package 并发编程.并发基础知识例子;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 有一个List，线程A向中添加10个元素，线程B监听其中元素，当list的个数为5的时候停止监听
 *
 * 以下方法浪费cpu 因为thread2 while(true)
 *
 */
public class T10 {

    volatile List list = new ArrayList();

    synchronized void add(Object o) {
        list.add(o);
    }

    synchronized int get() {
        return list.size();
    }

    public static void main(String[] args) {

        T10 t = new T10();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("thread1 add " + i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            while (true) {
                if (t.get() == 5) {
                    break;
                }
            }
            System.out.println("thread2 end");
        }, "thread2").start();

    }
}
