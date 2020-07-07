package com.study.cocurrent;

/**
 * 守护线程
 *
 * 如果只有守护线程的话程序会直接退出
 */
public class DaemonTest {

    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonT thread = new DaemonT();
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(200);

    }
}
