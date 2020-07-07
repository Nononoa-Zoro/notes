package 并发编程;

/**
 * 两个线程交替打印 A B
 */
public class 两个线程交替打印AB_Synchronized实现 {
    private Object object = new Object();
    private volatile int flag = 0;

    public void A() throws InterruptedException {
        synchronized (object) {
            while (true){
                if (flag != 0) {
                    object.wait();
                }
                flag = 1;
                System.out.println("A");
                object.notifyAll();
            }

        }
    }

    public void B() throws InterruptedException {
        synchronized (object){
            while (true){
                if (flag!=1){
                    object.wait();
                }
                flag=0;
                System.out.println("B");
                object.notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        两个线程交替打印AB_Synchronized实现 r = new 两个线程交替打印AB_Synchronized实现();
        new Thread(()->{
            try {
                r.A();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                r.B();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
