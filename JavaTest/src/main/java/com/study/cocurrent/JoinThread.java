package com.study.cocurrent;

/**
 * thread1.join() 表示当前线程愿意等待thread1执行完成再开始执行
 *
 * join 实际上是 调用线程wait当前join的线程  当join线程执行完成以后 调用notifyAll释放资源 调用线程可以继续执行
 */
public class JoinThread {

    public volatile static int i = 0;

    public static class addThread implements Runnable {
        @Override
        public void run() {
            for (i = 0; i < 1000000000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new addThread());
        thread.start();
        //thread.join() 表示 主线程愿意等待thread执行完成 再开始执行
        thread.join();
        System.out.println(i);
    }
}
