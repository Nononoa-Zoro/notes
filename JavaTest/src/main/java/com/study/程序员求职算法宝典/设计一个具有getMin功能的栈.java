package com.study.程序员求职算法宝典;

import java.util.Stack;

public class 设计一个具有getMin功能的栈 {
    Stack<Integer> stackData = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();

    public void push(int data){
        stackData.push(data);
        if(stackMin.isEmpty()||data<=stackMin.peek()){
            stackMin.push(data);
        }
    }

    public int pop(){
        Integer peek = stackData.peek();
        if(peek==stackMin.peek()){
            stackData.pop();
            return stackMin.pop();
        }
        return stackData.pop();
    }

    public int getMin(){
        return stackMin.peek();
    }


}
