package 并发编程.并发容器;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class T02_LinkedBlockingQueue {

    //阻塞队列 模拟生产者消费者
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {

        //一个生产者线程
        new Thread(()->{

            for(int i=0;i<100;i++){
                try {
                    strs.put("a"+i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"生产者").start();


        //5个消费者线程
        for(int i=0;i<5;i++){
            new Thread(()->{

                while (true){
                    try {
                        System.out.println(Thread.currentThread().getName()+" 获得 "+strs.take());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            },"消费者"+i).start();
        }


    }
}
