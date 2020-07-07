package 代理.CGLIB;


import org.springframework.cglib.proxy.Enhancer;

public class ProxyFactory {
    public Object createProxy(Object target) {
        //使用Enhancer对象创建目标类的代理对象
        //创建Enhancer对象
        Enhancer en = new Enhancer();
        //指定目标类 你要代理谁
        en.setSuperclass(SomeService.class);
        //指定方法拦截器对象，
        en.setCallback(new MyMethodInterceptor(target));
        //创建代理对象
        return en.create();

    }
}
