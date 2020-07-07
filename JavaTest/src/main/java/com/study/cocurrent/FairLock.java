package com.study.cocurrent;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {

    //构造一个公平锁 不会产生饥饿现象 当多个线程在等待同一个锁时 会按照先后顺序 分配锁
    //虽然ReentrantLock可以实现公平锁 但是这会维护一个等待队列 所以性能比较低

    //synchronized无法保证锁是公平的 所以可能会产生饥饿现象

    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            fairLock.lock();
            System.out.println(Thread.currentThread().getName()+"获得锁");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thread_t1");
        Thread t2 = new Thread(r1, "Thread_t2");
        t1.start();
        t2.start();

    }
}
