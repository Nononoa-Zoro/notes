package 并发编程.并发基础知识例子;

public class T1 implements Runnable {

    private  int count = 10;

    /**
     * synchronized保证下面的方法全是原子性的操作
     *
     * 原子性：整个方法都会全部执行 或者全部不执行
     */
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + ":count is :" + count);
    }

    public static void main(String[] args) {
        T1 t = new T1();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "THREAD " + i).start();
        }
    }
}
