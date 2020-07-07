package 并发编程;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class 十个线程把i加到10 {
    private static CountDownLatch latch = new CountDownLatch(10);
    private static AtomicInteger i = new AtomicInteger(0);

    public void add() {
        i.getAndIncrement();
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        十个线程把i加到10 r = new 十个线程把i加到10();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                r.add();
            }).start();
        }
        latch.await();
        System.out.println(i);
    }
}
