package com.study.pattern;

public interface Observable {

    //增加一个观察者
    public void add(Observer observer);

    //删除一个观察者
    public void remove(Observer observer);

    //通知所有的观察者 我更新了
    public void notifyObservers(String context);


}
