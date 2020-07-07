package com.study.pattern;

public class Vendor_Dynamic implements Sell{
    @Override
    public void sell() {
        System.out.println("in sell() method");
    }

    @Override
    public void ad() {
        System.out.println("in ad() method");
    }
}
