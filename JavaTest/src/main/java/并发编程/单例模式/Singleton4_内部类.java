package 并发编程.单例模式;

//单例模式的实现4
//内部类的实现
//优点：符合懒初始化 只有在调用getInstance方法的时候才会初始化instance
//缺点：无法传参
public class Singleton4_内部类 {

    private Singleton4_内部类() {

    }

    private static class Inner{
        private  static Singleton4_内部类 instance = new Singleton4_内部类();
    }

    public static Singleton4_内部类 getInstance(){
        return Inner.instance;
    }

}
