package 并发编程;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 三个线程按照顺序打印 A B C
 */

public class 按顺序打印ABC {

    int count=1;

    final Object lock = new Object();

    void A(){
        synchronized(lock){
            if(count!=1){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" print A");
            count++;
            lock.notifyAll();
        }
    }

    void B(){
        synchronized(lock){
            if(count!=2){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" print B");
            count++;
            lock.notifyAll();
        }
    }

    void C(){
        synchronized(lock){
            if(count!=3){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" print C");
            count++;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {

        按顺序打印ABC t = new 按顺序打印ABC();

        new Thread(t::A,"t1").start();
        new Thread(t::B,"t2").start();
        new Thread(t::C,"t3").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
