package 并发编程.指令重排序;

public class Test {
    int a = 0, b = 0;

    //线程1
    public void A() {
        a = 1;
        b = 2;
    }

    //线程2
    public int B() {
        return a;
    }

    //线程3
    public int C() {
        return b;
    }

    public static void main(String[] args) {
        Test t = new Test();
        new Thread(() -> {
            t.A();
        }, "A").start();

        new Thread(() -> {
            System.out.println(t.B());
        }, "B").start();

        new Thread(() -> {
            System.out.println(t.C());
        }, "C").start();
    }

}
