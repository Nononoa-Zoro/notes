package com.study.cocurrent;

/**
 * Thread.interrupt() 中断线程 通知线程有人希望你退出 设置中断标志位
 * Thread.isInterrupted() 判断是否被中断
 * Thread.interrupted  static方法 判断是否有中断标志位 如果有 移除中断标志位
 */
public class InterruptThread {


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("退出");
                        break;
                    }
                    try {
                        //如果在线程sleep时 中断 会清除中断标志位 所以需要加上中断标志
                        Thread.sleep(120);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted while sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        thread1.start();
        Thread.sleep(100);
        thread1.interrupt();
    }
}
