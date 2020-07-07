package com.study.concurrent.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(6000);
                return new Random().nextInt();
            }
        };

        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();
        Thread.sleep(1000);
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());
        System.out.println(future.get());
    }

}
