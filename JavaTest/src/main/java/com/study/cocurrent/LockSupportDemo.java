package com.study.cocurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park()阻塞当前线程 尝试去申请一个permit
 * LockSupport.unPark()释放当前线程的permit
 */
public class LockSupportDemo {
    public static Object u =new Object();
    public static ChangeObjectThread t1=new ChangeObjectThread("thread1");
    public static ChangeObjectThread t2=new ChangeObjectThread("thread2");

    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in"+this.getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
