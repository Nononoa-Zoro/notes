package 并发编程.reentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * 使用synchronized来实现同步 对比ReentrantLock实现同步
 *
 * m1和m2都是锁定在堆内存的ReentrantLock1上的
 *
 * 因此同一时刻只有一个方法可以执行
 */
public class ReentrantLock1 {
    synchronized void m1(){
        for(int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2(){
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        ReentrantLock1 r = new ReentrantLock1();

        new Thread(r::m1,"thread1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(r::m2,"thread2").start();
    }
}
