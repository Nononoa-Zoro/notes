package com.study.concurrent.threadpool;

import java.io.IOException;
import java.util.concurrent.*;

public class RejectThreadDemo {

    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+" Thread ID: "+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws
                InterruptedException {

            MyTask myTask = new MyTask();

            ThreadPoolExecutor es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    System.out.println(r.toString() + "is discard.");
                }
            });

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                es.submit(myTask);
                Thread.sleep(100);
            }
        }
    }
}
