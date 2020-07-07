package com.study.zuoshen;

/**
 * 使用数组实现栈
 */
public class ArrayStack {
    public Integer[] arr;
    public Integer index;

    public ArrayStack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("the size cannot be less than zero");
        }
        this.arr = new Integer[initSize];
        index = 0;
    }

    public Integer peek(){
        if(index==0){
            return null;
        }
        return arr[index-1];
    }

    public void push(Integer i){
        if(index==arr.length){
            throw new IllegalArgumentException("out of bound");
        }
        arr[index++]=i;
    }

    public Integer pop(){
        if(index==0){
            throw new IllegalArgumentException("the stack is empty");
        }
        return arr[--index];
    }
}
