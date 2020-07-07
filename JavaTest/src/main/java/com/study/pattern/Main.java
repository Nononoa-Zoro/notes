package com.study.pattern;

import org.junit.Test;

import java.lang.reflect.Proxy;

public class Main {
    @Test
    public void ob_pattern() {
        //被观察者
        HanFeiZi hanFeiZi = new HanFeiZi();
        //观察者
        LiSi liSi = new LiSi();
        hanFeiZi.add(liSi);
        hanFeiZi.haveBreakfast();

    }

    //静态代理
    @Test
    public void test_Static_Proxy() {
        //被代理类
        Vendor vendor = new Vendor();
        //代理类
        WeixinAgent proxy = new WeixinAgent(vendor);
        proxy.sell();
        proxy.ad();
    }

    //动态代理
    @Test
    public void test_Dynamic_Proxy() {
        Vendor vendor = new Vendor();
        DynamicProxy dynamicProxy = new DynamicProxy(vendor);
        Sell sell = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class}, dynamicProxy));
        sell.ad();
        sell.sell();
    }

    //组合模式的测试
    @Test
    public void test_composite(){

        Folder DSFolder = new Folder("设计模式资料");
        File note1 = new File("组合模式笔记.md", "组合模式组合多个对象形成树形结构以表示具有 \"整体—部分\" 关系的层次结构");
        File note2 = new File("工厂方法模式.md", "工厂方法模式定义一个用于创建对象的接口，让子类决定将哪一个类实例化。");
        DSFolder.add(note1);
        DSFolder.add(note2);

        Folder codeFolder = new Folder("样例代码");
        File readme = new File("README.md", "# 设计模式示例代码项目");
        Folder srcFolder = new Folder("src");
        File code1 = new File("组合模式示例.java", "这是组合模式的示例代码");

        srcFolder.add(code1);
        codeFolder.add(readme);
        codeFolder.add(srcFolder);
        DSFolder.add(codeFolder);

        DSFolder.print();

    }
}
