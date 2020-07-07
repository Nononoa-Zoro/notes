package com.study.concurrent.threadpool;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executors创建线程池
 */
public class ThreadPoolDemo1 {
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("当前时间：" + System.currentTimeMillis() + "线程ID： " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.submit(myTask);
        }
    }
}
