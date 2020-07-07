package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 线程抛出异常后锁会默认释放锁
 */
public class T4 {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count: "+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if(count==5){
                int i=1/0;//这里如果出现异常会释放锁
            }
        }
    }

    public static void main(String[] args) {
        T4 t = new T4();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(r,"t2").start();
    }
}
