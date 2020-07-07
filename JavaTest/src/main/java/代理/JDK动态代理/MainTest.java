package 代理.JDK动态代理;


import java.lang.reflect.Proxy;

public class MainTest {
    public static void main(String[] args) {

        Star liudehua = new LiuDeHua();

        StarProxy proxy = new StarProxy();

        //incocationHandler要代理谁
        proxy.setTarget(liudehua);

        //这是代理类
        Star liudehuaProxy = (Star) Proxy.newProxyInstance(liudehua.getClass().getClassLoader(), liudehua.getClass().getInterfaces(), proxy);

        //最后是代理类在做这件事 并且在真实的类前后加上处理条件
        liudehuaProxy.sing("hello");
    }

}
