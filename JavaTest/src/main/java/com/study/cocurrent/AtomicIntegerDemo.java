package com.study.cocurrent;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 无锁线程整数AtomicInteger
 * CAS
 */

public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
    static volatile int a;
    static ReentrantLock lock=new ReentrantLock();
    public static class  AddThread implements Runnable{
        @Override
        public void run() {
            for (int k=0;k<10000;k++){
                i.incrementAndGet();
            }
        }
    }

    public static class AddThread1 implements Runnable{
        @Override
        public void run() {
            synchronized (AddThread1.class){
                for(int k=0;k<10000;k++){
                    a++;
                }
            }
//            try {
//                lock.lockInterruptibly();
//                for(int k=0;k<10000;k++){
//                    a++;
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread1());
        }
        for (int k=0;k<10;k++){
            ts[k].start();
        }
        for (int k=0;k<10;k++){
            ts[k].join();
        }
        System.out.println(a);
    }
}
