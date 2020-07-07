package com.study.leecode;

/**
 * char to int ---> Character.getnumericvalue
 * int to char ---> string.valueof(int).tochararray
 *
 */
public class Test8 {
    public static void main(String[] args) {
        System.out.println("输出'0'~'9'的所有char类型字符（还是char类型）");
        for (char c='0';c<='9';c++) {
            System.out.print(c+" ");
        }

        System.out.println();

        System.out.println("输出'0'~'9'的所有char类型字符的int型字面值（int类型）");
        for (char c='0';c<='9';c++) {
            System.out.print(Character.getNumericValue(c)+" ");
        }

        System.out.println();
        System.out.println("输出'0'~'9'的所有char类型字符的ASCII值");
        for (char c='0';c<='9';c++) {
            System.out.print((int)(c)+" ");
        }
        System.out.println();
        int a=9;
        char[] chars = String.valueOf(a).toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
        }
    }
}
