package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;

public class T8 {

    Object o = new Object();

    void m(){
        //synchronized锁定的对象o不能指向新的对象 否则就会失效
        synchronized(o){
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T8 t = new T8();

        new Thread(t::m,"thread1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t.o=new Object();//当锁定的对象指向另外一个新的引用时原来的锁定就会失效

        Thread t2 = new Thread(t::m,"thread2");

        t2.start();
    }

}
