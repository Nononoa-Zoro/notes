package com.study.leecode;

public class 钱币找零问题 {

    //面额
    static int[] values = {1, 2, 5, 10, 20, 50, 100};
    //数量
    static int[] counts = {3, 3, 2, 1, 1, 3, 3};

    public static void main(String[] args) {
        int[] number = getNumber(423, values, counts);
        for(int i=0;i<values.length;i++){
            System.out.println(values[i]+" : "+number[i]);
        }
    }

    public static int[] getNumber(int sum, int[] value, int[] counts) {
        int[] result = new int[7];
        //表示已经凑出来的面额值
        int add = 0;
        //面额从大到小遍历
        for (int i = values.length - 1; i >= 0; i--) {
            int num = (sum - add) / values[i];
            //所需面额数量大于 最大的面额数量
            if (num > counts[i]) {
                num = counts[i];
            }
            add += num * values[i];
            result[i] = num;
        }
        return result;

    }
}
