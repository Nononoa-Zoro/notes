package com.study.test;

/**
 * 如果try子句中有return 仍然会执行finally
 * 1.执行try块中的代码
 * 2.try 块的return的表达式
 * 3.执行finally块（如果出现了异常肯定先去catch）
 * 4.最终的return
 */
public class try_finally执行顺序 {

    public static String method(){
        try {
            return "a";
        }finally{
            System.out.println("wtf");
            return "b";
        }
    }

    public static void main(String[] args) {
        System.out.println(method());//b
    }
}
