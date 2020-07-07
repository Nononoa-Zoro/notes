package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * volatile修饰的变量在获取值的时候都会从主存中读取数据
 *
 * 并刷新到当前线程的工作内存中
 *
 * Atomic类也可以保证可见性
 *
 * Atomic类可以保证原子性操作
 */
public class T5 {

    //volatile boolean flag =true;
    AtomicBoolean flag=new AtomicBoolean(true);

    void m(){
        while (flag.get()){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T5 t5 = new T5();
        new Thread(t5::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //t5.flag=false;
        t5.flag.set(false);
    }
}
