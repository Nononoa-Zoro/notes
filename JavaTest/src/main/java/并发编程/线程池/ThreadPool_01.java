package 并发编程.线程池;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool_01 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0;i<6;i++){
            es.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(es);

        es.shutdown();
        System.out.println(es.isTerminated());//线程池里的任务是否执行完
        System.out.println(es.isShutdown());//线程池是否在关闭状态，关闭不代表线程池里的任务不执行了
        System.out.println(es);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(es.isTerminated());
        System.out.println(es.isShutdown());
        System.out.println(es);
    }
}
