package 并发编程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 两个线程交替打印AB_Lock实现 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile int flag = 0;

    public void A() {
        try {
            while (true) {
                lock.lock();
                if (flag == 0) {
                    flag=1;
                    System.out.println("A");
                    condition.signalAll();
                } else {
                    condition.await();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void B(){
        try {
            while (true){
                lock.lock();
                if(flag==1){
                    flag=0;
                    System.out.println("B");
                    condition.signalAll();
                }else{
                    condition.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        两个线程交替打印AB_Lock实现 r = new 两个线程交替打印AB_Lock实现();
        new Thread(()->{
            r.A();
        },"t1").start();
        new Thread(()->{
            r.B();
        },"t2").start();
    }
}
