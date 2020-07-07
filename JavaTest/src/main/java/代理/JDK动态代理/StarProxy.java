package 代理.JDK动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StarProxy implements InvocationHandler {

    //你要代理谁
    private Object target;

    public void setTarget(Object target)
    {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("刘德华唱歌之前");
        //target对象调用method方法
        Object result = method.invoke(target, args);
        System.out.println("刘德华唱歌之前");
        return result;
    }


}
