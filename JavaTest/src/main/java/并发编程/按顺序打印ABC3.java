package 并发编程;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程按照顺序打印 A B C
 * 版本3
 * 使用countDownLatch
 */

public class 按顺序打印ABC3 {


    CountDownLatch latch = new CountDownLatch(3);

    void A(){
        try {
            if(latch.getCount()!=3){
                latch.await();
            }
            System.out.println(Thread.currentThread().getName()+" print A ");
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    void B(){
        try {
            if(latch.getCount()!=2){
               latch.await();
            }
            System.out.println(Thread.currentThread().getName()+" print B ");
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    void C(){
        try {
            if(latch.getCount()!=1){
                latch.await();
            }
            System.out.println(Thread.currentThread().getName()+" print C ");
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        按顺序打印ABC3 t = new 按顺序打印ABC3();

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
