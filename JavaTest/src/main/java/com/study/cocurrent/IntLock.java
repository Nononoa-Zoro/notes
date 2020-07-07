package com.study.cocurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * java并发中 解决死锁的办法之一
 *
 * ReentrantLock 可重入锁
 *
 * ReentrantLock.lockInterruptibly 表示可以相应外部中断 不需要一直等待锁资源
 *
 * 另外一种方式是tryLock 设定当前线程尝试获得锁的等待时间 如果时间到了还没有锁资源 就不再等待锁资源
 *
 * 1.响应中断lockInterruptibly
 *
 * 2.限时等待tryLock(long timeout)
 *
 */
public class IntLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

       if(lock==1){
           try {
               if(lock==1){
                   lock1.lockInterruptibly();
                   Thread.sleep(500);
                   lock2.lockInterruptibly();
               }else{
                   lock2.lockInterruptibly();
                   Thread.sleep(500);
                   lock1.lockInterruptibly();
               }
           }catch (InterruptedException e){
               e.printStackTrace();
           }finally {
               if(lock1.isHeldByCurrentThread())lock1.unlock();
               if(lock2.isHeldByCurrentThread())lock2.unlock();
               System.out.println(Thread.currentThread().getId()+" :线程退出");
           }

       }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();thread2.start();
        thread1.sleep(1000);
        //通知 thread2中断 别等待锁了
        thread2.interrupt();
    }
}
