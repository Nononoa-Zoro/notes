package 并发编程.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal为每一个线程保存一个变量的副本
 */
public class ThreadLocal2 {

    static ThreadLocal<Person1> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //看不见下面的线程对变量的设置 所以这里get出来是null
            System.out.println(tl.get());
        },"thread1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person1("lisi"));
        },"thread2").start();

    }
}

class Person1{

    String name = "zhangsan";

    public Person1(String name) {
        this.name = name;
    }
}
