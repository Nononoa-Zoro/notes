package 并发编程.并发基础知识例子;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 保证可见性 不保证原子性
 * synchronized 保证原子性
 *
 * AtomicInteger 保证原子操作
 */
public class T7 {
    /*volatile*/ AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 100000; i++) count.incrementAndGet();//count++;
    }

    public static void main(String[] args) {

        T7 t = new T7();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) threads.add(new Thread(t::m, "thread-" + i));

        threads.forEach(Thread::start);

        long start = System.currentTimeMillis();
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(t.count);
        System.out.println("Atomic 花费时间 " + (end - start));
    }
}
