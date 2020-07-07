package 并发编程.并发基础知识例子;
/**
 * 在一个synchronized方法执行的时候  非synchronized方法 也可以执行
 *
 * synchronized锁定的是堆内存上的对象
 *
 * static synchronized锁定的是  类的class文件
 */
public class T2 {

    public  synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start");
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"m2 end");
    }


    public static void main(String[] args) {
        T2 t = new T2();
        new Thread(t::m1,"Thread1").start();
        new Thread(t::m2,"Thread2").start();
    }
}
