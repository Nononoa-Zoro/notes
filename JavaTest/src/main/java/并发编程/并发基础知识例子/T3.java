package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 * 即在方法m1中可以调用m2 对T3这个对象加了两次锁
 * 这把锁上会有计数器表示有多少个对象对它加了锁
 */
public class T3 {

    public synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        m2();
    }

    public synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m2");
    }
}
