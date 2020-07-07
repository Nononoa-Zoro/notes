package 并发编程.手动实现生产者消费者;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定大小的容器
 * 要求支持两个生产者线程以及10个消费者线程
 * <p>
 * 下面使用lock/condition实现
 */
public class Container2<T> {

    final private LinkedList<T> list = new LinkedList<>();

    final private int MAX = 10;//最多容纳10个元素

    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();//容器满的时候 生产者等待
            }
            //容器不满的操作
            list.add(t);
            count++;
            System.out.println(Thread.currentThread().getName()+"生产");
            consumer.signalAll();//生产一个商品之后 唤醒消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();//容器空的时候 消费者等待
            }
            t = list.removeFirst();
            count--;
            System.out.println(Thread.currentThread().getName()+"消费");
            producer.signalAll();//消费一个商品之后 通知生产者线程开始生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }


    public static void main(String[] args) {
        Container2<String> container = new Container2<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                container.get();
            }, "消费者" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                container.put("123");
            }, "生产者" + i).start();
        }
    }

}
