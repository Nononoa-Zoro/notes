package com.study.cocurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    //普通可重入锁
    private static Lock lock = new ReentrantLock();

    //读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock= readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock=readWriteLock.writeLock();
    private  int value;

    public Object handRead(Lock lock)throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handWrite(Lock lock,int index)throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            this.value=index;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handWrite(writeLock,new Random().nextInt());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };


        for (int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }

        for (int i=18;i<20;i++){
            new Thread(writeRunnable).start();
        }

        long end = System.currentTimeMillis();

    }

}
