package com.study.cocurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semaphore 信号量 可以实现控制多个线程访问统同一个资源
 */
public class SemaphoreDemo implements Runnable {

    //允许最多5个线程访问资源
    final Semaphore semp = new Semaphore(5);
    private static final CountDownLatch cdl=new CountDownLatch(20);
    @Override
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + "done");
            semp.release();
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(20);
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            es.submit(demo);
        }
        long start = System.currentTimeMillis();
        cdl.await();
        long end = System.currentTimeMillis();
        System.out.println((end-start)+"ms");
    }
}

