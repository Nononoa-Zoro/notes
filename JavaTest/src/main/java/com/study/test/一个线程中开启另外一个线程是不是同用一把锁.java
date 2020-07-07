package com.study.test;


import java.util.concurrent.TimeUnit;

//可以
public class 一个线程中开启另外一个线程是不是同用一把锁 {
    static class M {
        synchronized void A(){
            System.out.println("A");
        }


        synchronized void B(){
            System.out.println("B");
        }

    }
    public static void main(String[] args) throws InterruptedException {

        M m = new M();
        new Thread(()->{

            m.A();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(()->{
                m.B();
            },"B").start();

        },"A").start();

        //打印AB
        Thread.sleep(1000);
    }
}
