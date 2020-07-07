package com.study.cocurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {

        private String name;

        private final CyclicBarrier cyclicBarrier;

        public Soldier(String name, CyclicBarrier cyclicBarrier) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待所有士兵完成工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //BrokenBarrierException 表示cyclicBarrier无法正常工作
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 100000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " mission success");
        }
    }

    //cyclicBarrier 在等待所有线程完成技术任务之后 执行的动作
    public static class BarrierRun implements Runnable {

        boolean flag;//10个士兵集合完毕 flag=true

        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[" + N + "个士兵 任务完成]");
            } else {
                System.out.println("司令：[" + N + "个士兵 集合完毕]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报到");
            //创建线程需要绑定 cyclicBarrier
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclic));
            allSoldier[i].start();
        }
    }
}
