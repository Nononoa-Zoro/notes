package com.study.sort;

import java.util.Scanner;
import java.util.function.IntPredicate;

/**
 * 插入排序 好比打扑克牌
 * <p>
 * 插入排序是稳定的排序算法
 */
public class InsertSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        while (caseNum-- > 0) {
            int len = scanner.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = scanner.nextInt();
            }
            StringBuilder res = new StringBuilder();
            insertSort(arr);
            for (int i : arr) {
                res.append(i).append(" ");
            }
            System.out.println(res.toString().trim());
        }
    }

    private static void insertSort(int[] arr) {
        int i, j, t;
        int len = arr.length;
        for (i = 1; i < len; i++) {
            //例子： 1 2 5 | 4 3
            //前面的1 2 5 已经有序此时4比5小，所以在前面有序的序列中找一个比4小的元素2，然后插入这个元素的后面
            //在后面的未排序序列B中发现一个比前面已排序的序列A的最后一个元素小，则去前面找一个位置插入
            if (arr[i] < arr[i - 1]) {
                t = arr[i];
                //从前面找一个比t小的位置插入
                //循环退出时arr[j]<=t 所以插入的位置应该时j+1
                for (j = i - 1; j >= 0 && arr[j] > t; j--) arr[j + 1] = arr[j];
                arr[j + 1] = t;
            }
        }
    }

}
