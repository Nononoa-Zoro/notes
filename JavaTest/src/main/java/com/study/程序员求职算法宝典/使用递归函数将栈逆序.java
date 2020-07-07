package com.study.程序员求职算法宝典;

import java.util.Stack;

public class 使用递归函数将栈逆序 {

    //获取并删除栈的最后一个元素
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        Integer result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }

        int last = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(last);

    }
}
