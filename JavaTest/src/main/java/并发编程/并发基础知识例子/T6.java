package 并发编程.并发基础知识例子;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 保证可见性 不保证原子性
 * synchronized 保证原子性
 */
public class T6 {
     /*volatile*/ int count = 0;
     synchronized void m(){
        for(int i=0;i<100000;i++)count++;
    }

    public static void main(String[] args) {

        T6 t = new T6();

        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<10;i++)threads.add(new Thread(t::m,"thread-"+i));

        threads.forEach(Thread::start);
        long startTime = System.currentTimeMillis();

        threads.forEach(thread-> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
        long endTime = System.currentTimeMillis();
        System.out.println("Synchronized 花费了："+ (endTime - startTime));
    }
}
