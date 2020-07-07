package com.study.pattern;

/**
 * 代理类持有一个被代理类的引用
 * <p>
 * 静态代理模式还可以使client和被代理类之间解耦
 */
public class WeixinAgent implements Sell {
    private Vendor vendor;

    public WeixinAgent(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public void sell() {
        System.out.println("before sell()");
        vendor.sell();
        System.out.println("after sell()");
    }

    @Override
    public void ad() {
        System.out.println("before ad()");
        vendor.ad();
        System.out.println("after ad()");
    }
}
