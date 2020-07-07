package com.study.剑指offer;

import java.util.Stack;

public class 实现min函数的栈 {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
        if(stack2.isEmpty()||node<stack2.peek()){
            stack2.push(node);
        }
    }

    public void pop() {
        Integer peek = stack1.pop();
        if(peek==stack2.peek()){
            stack2.pop();
        }
    }

    public int top() {
        Integer peek = stack1.peek();
        return peek;
    }

    public int min() {
        if(!stack2.isEmpty()){
            return stack2.peek();
        }
        return -1;
    }
}
