package com.study.cocurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TryLock 限时等待
 *
 * tryLock() 不带任何参数 如果获得锁就立刻执行 没得到就释放锁
 * tryLock(5,TimeUnit.SECONDS) 表示等待5秒 如果获得就执行 没得到就释放锁
 *
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1=new ReentrantLock();

    public static ReentrantLock lock2=new ReentrantLock();

    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if(lock==1){
                while (true){
                    if(lock1.tryLock()){
                        Thread.sleep(500);
                        lock1.unlock();
                        if(lock2.tryLock()){
                            System.out.println(Thread.currentThread().getId()+"job done");
                            lock2.unlock();
                        }
                    }
                }
            }else {
                while (true){
                    if(lock2.tryLock()){
                        Thread.sleep(500);
                        lock2.unlock();
                        if(lock1.tryLock()){
                            System.out.println(Thread.currentThread().getId()+"job done");
                            lock1.unlock();
                        }
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
        }
    }

    public static void main(String[] args) {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
    }
}
