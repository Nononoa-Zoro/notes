package com.study.cocurrent;

/**
 * 创建一个线程
 * 实现runnable接口
 */
public class CreateThread implements Runnable {

    private String name;

    public CreateThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("I'm thread " + name);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new CreateThread("zh"));
        thread.start();
    }

}
