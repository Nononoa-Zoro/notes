package com.study.cocurrent;

/**
 * 创建线程组
 *
 */
public class ThreadGroupName implements Runnable{
    @Override
    public void run() {
        String str = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true){
            System.out.println(str);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");
        //线程组名字 线程 线程名字
        Thread t1 = new Thread(threadGroup, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(threadGroup, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }
}
