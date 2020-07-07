package com.study.pattern;

public class LiSi implements Observer {
    @Override
    public void upDate(String context) {
        System.out.println("来自被观察者的消息： "+context);
        System.out.println("李斯：发现韩非子在吃饭");
    }
}
