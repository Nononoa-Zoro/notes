package 代理.CGLIB;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {

    private Object target;

    public MyMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * intercept特点：截取对目标方法的调用
     * 参数：
     * Object obj：代理对象
     * Method method：目标方法
     * Object[] args：方法参数
     * MethodProxy proxy：方法的代理对象
     * 返回值：
     * Object：目标方法的执行结果
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("=========intercept=========");
        //调用目标方法
        Object result = method.invoke(target, args);
        if (result != null) {
            String str = (String) result;
            result = str.toUpperCase();
        }

        return result;
    }


}
