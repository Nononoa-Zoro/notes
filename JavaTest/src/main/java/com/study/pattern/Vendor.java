package com.study.pattern;

/**
 * 厂家
 */
public class Vendor implements Sell{
    @Override
    public void sell() {
        System.out.println("In Vendor's sell method.");
    }

    @Override
    public void ad() {
        System.out.println("In Vendor's ad method");
    }
}
