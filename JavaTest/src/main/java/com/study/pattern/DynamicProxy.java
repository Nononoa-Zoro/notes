package com.study.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    //obj是委托对象 Vendor实现了sell接口
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * @param proxy  被代理的对象 在这里是Vendor
     * @param method 被代理的方法
     * @param args   被代理方法的参数
     * @return 被代理类的调用结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        String name = method.getName();
        if (name == "ad") {
            //调用目标方法 这里就是调用Vendor的ad()
            Object result = method.invoke(obj, args);
            System.out.println("after");
            return result;
        }
        System.out.println("after");
        return null;

    }
}
