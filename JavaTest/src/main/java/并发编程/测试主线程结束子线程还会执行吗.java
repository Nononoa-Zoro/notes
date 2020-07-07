package 并发编程;

import java.util.concurrent.TimeUnit;

/**
 * 如果主线程执行完 子线程还没有执行完 也会等待主线程执行完
 */
public class 测试主线程结束子线程还会执行吗 {

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                System.out.println("子线程"+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("done");
    }
}
