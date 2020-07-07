package 并发编程.单例模式;

//懒汉式 在方法调用之后才初始化instance 线程不安全 改进方法 见Singleton2
public class Singleton_懒汉式 {

    private static Singleton_懒汉式 instance = null;

    private Singleton_懒汉式(){

    }

    public static Singleton_懒汉式 getInstance(){
        if(instance==null){
            instance=new Singleton_懒汉式();
        }
        return instance;
    }
}
