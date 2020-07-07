package 并发编程.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized/reentrantLock都是可重入锁，又叫做递归锁
 * 指的是当同一个线程的外层函数获得锁以后，内层函数同样可以获得这把锁
 * <p>
 * 线程可以进入任意一个它已经获得锁的同步代码块
 */
class Phone implements Runnable {

    public synchronized void sendSMS() throws Exception {

        System.out.println(Thread.currentThread().getName() + "\t" + "invoke sendSMS method");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {

        System.out.println(Thread.currentThread().getName() + "\t" + "invoke sendEmail method");

    }


    Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + " invoke get method");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "invoke set method");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }
}

public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(phone, "t1").start();
        new Thread(phone, "t2").start();


//        new Thread(()->{
//            try {
//                phone.sendSMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t1").start();
//
//        new Thread(()->{
//            try {
//                phone.sendSMS();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t2").start();
    }
}
