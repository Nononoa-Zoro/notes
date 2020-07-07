package com.study.pattern;

import java.util.ArrayList;
import java.util.List;

public class HanFeiZi implements Observable {

    private List<Observer> observers = new ArrayList<>();

    //类似addListener 注册回调方法
    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String context) {
        //observer 是一个接口 这里接口调用接口的方法 其实就是回调
        for (Observer observer : observers) {
            observer.upDate(context);
        }
    }

    public void haveBreakfast(){
        System.out.println("韩非子开始吃饭");
        notifyObservers("韩非子开始吃饭");
    }
}
