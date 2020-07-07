package 并发编程.单例模式;

//单例模式的实现2
//同步方法
//缺点是锁粒度太大 改进见 Singleton3
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {

    }

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
