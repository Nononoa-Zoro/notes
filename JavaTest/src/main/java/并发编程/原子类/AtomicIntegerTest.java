package 并发编程.原子类;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(5, 2020);
        System.out.println(b + "\t" + atomicInteger.get());
        boolean b1 = atomicInteger.compareAndSet(5, 2021);
        System.out.println(b1 + "\t" + atomicInteger.get());

    }
}
