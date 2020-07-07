package 并发编程;

import 并发编程.reentrantLock.ReentrantLock1;

import javax.sound.midi.Soundbank;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 同时启动10个线程_计数器实现 {

    static volatile int count = 10;

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public void method() {
        while (count != 0) {
            try {
                lock.lock();
                count--;
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 开始工作... at " + System.currentTimeMillis());
    }

    public void signalAll() {
        while (true) {
            try {
                lock.lock();
                if (count == 0) {
                    condition.signalAll();
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {

        同时启动10个线程_计数器实现 r = new 同时启动10个线程_计数器实现();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                r.method();
            }, i + " ").start();
        }

        r.signalAll();

    }

}
