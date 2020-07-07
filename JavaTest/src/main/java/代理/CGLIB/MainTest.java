package 代理.CGLIB;

public class MainTest {
    public static void main(String[] args) {

        //创建目标对象
        SomeService target = new SomeService();

        //创建工具类
        ProxyFactory factory  = new ProxyFactory();

        //创建代理对象
        SomeService proxy  = (SomeService) factory.createProxy(target);

        System.out.println("代理对象父类名称:"+proxy.getClass().getSuperclass().getName());
        System.out.println("代理类名称:"+proxy.getClass().getName());

        //通过代理对象执行方法。实现功能增强
        String str = proxy.doSome();
        System.out.println("str:"+str);

    }
}
