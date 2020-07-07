package 并发编程.手动实现生产者消费者;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 固定大小的容器
 * 要求支持两个生产者线程以及10个消费者线程
 * <p>
 * 下面使用wait/notify实现
 */
public class Container1<T> {
    
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;//最多容纳10个元素
    private int count=0;

    public synchronized void put(T t){
        while(list.size()==MAX){
            //想想为什么要用while不用if
            //如果用if 那么当一个生产者线程拿到锁的时候开始继续往下执行，但是在list.add之前有其他线程往list中添加了一个元素，此时就超出了容量
            //所以wait一般都是和while一起使用的
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        this.notifyAll();//通知消费者线程拿数据
    }

    public synchronized T get(){
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t = list.removeFirst();
        count--;
        notifyAll();//通知生产者线程放数据
        return t;
    }

    public static void main(String[] args) {
        Container1<String> container = new Container1<>();

        for(int i=0;i<10;i++){
            new Thread(()->{
                container.get();
            },"消费者"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for(int i=0;i<2;i++){
            new Thread(()->{
                container.put("123");
            },"生产者"+i).start();
        }
    }

}
