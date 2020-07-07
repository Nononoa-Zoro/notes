package com.study.cocurrent;

/**
 * wait notify 是在Object下的两个方法
 * <p>
 * obj.wait 将线程A加入obj的等待队列
 * obj.notify 在obj的等待队列中随机选择一个线程唤醒
 * <p>
 * 在使用wait notify 之前 需要获得监视器 所以需要在synchronized代码块中进行wait和notify操作
 */
public class WaitAndNotify {

    private static final Object obj = new Object();

    public static class T1 extends Thread {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "  T1 start...");

                try {
                    System.out.println(System.currentTimeMillis() + "  T1 wait for object...");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"  T1 get obj");
                System.out.println(System.currentTimeMillis() + "  T1 end...");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "  T2 start... notify one thread");
                obj.notify();
                System.out.println(System.currentTimeMillis() + "  T2 end");

                //sleep 2s 让出cpu资源
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 thread1 = new T1();
        T2 thread2 = new T2();
        thread1.start();
        thread2.start();
    }
}
