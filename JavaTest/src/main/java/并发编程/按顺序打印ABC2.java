package 并发编程;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程按照顺序打印 A B C
 */

public class 按顺序打印ABC2 {

    int count=1;

    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    void A(){
        lock.lock();
        try {
            if(count!=1){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" print A ");
            count++;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    void B(){
        lock.lock();
        try {
            if(count!=2){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" print B ");
            count++;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    void C(){
        lock.lock();
        try {
            if(count!=3){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" print C ");
            count++;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        按顺序打印ABC2 t = new 按顺序打印ABC2();

        new Thread(t::A,"t1").start();
        new Thread(t::B,"t2").start();
        new Thread(t::C,"t3").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
