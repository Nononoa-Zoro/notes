package 并发编程;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class 同时启动10个线程_CyclicBarrier {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
        @Override
        public void run() {
            System.out.println("10个线程集合完毕");
        }
    });

    static class MyTask implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+" wait !!!");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName()+" work... ");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new MyTask()).start();
        }
    }



}
