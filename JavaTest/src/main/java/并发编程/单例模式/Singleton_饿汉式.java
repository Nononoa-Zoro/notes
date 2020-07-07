package 并发编程.单例模式;


//单例模式的实现1 饿汉式
//在类初始化的时候就在内存钟创建了对象 以空间换时间 不会引起多线程的问题
public class Singleton_饿汉式 {

    private static Singleton_饿汉式 instance = new Singleton_饿汉式();

    private Singleton_饿汉式(){

    }

    public static Singleton_饿汉式 getInstance(){
        return instance;
    }
}
