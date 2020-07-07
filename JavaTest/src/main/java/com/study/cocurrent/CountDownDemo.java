package com.study.cocurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownDemo implements Runnable{

    //需要10个线程完成任务
    private static  final CountDownLatch end= new CountDownLatch(10);

    private static final CountDownDemo demo = new CountDownDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("task complete...");
            //计数器减1
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++){
            es.submit(demo);
        }
        long start = System.currentTimeMillis();
        //主线程等待所有10个线程执行完成
        end.await();
        long end = System.currentTimeMillis();

        System.out.println("10个线程执行完成,耗时"+(end-start)+"ms");
        es.shutdown();
    }
}
