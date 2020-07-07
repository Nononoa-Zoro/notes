package com.study.zuoshen;

import java.util.Arrays;

/**
 * 荷兰国旗问题
 *
 * 给定一个整数数组，给定一个值K，这个值在原数组中一定存在
 * 要求把数组中小于K的元素放到数组的左边，大于K的元素放到数组的右边，等于K的元素放到数组的中间
 * 最终返回一个整数数组，其中只有两个值，分别是等于K的数组部分的左右两个下标值。
 *
 * 例如，给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4
 * 那么经过处理原数组可能得一种情况是：[2, 3, 1, 1, 4, 9, 7, 6, 5]
 * 需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，返回等于4部分的左右两个下标，即[4, 4]
 */
public class 荷兰国旗问题 {
    /**
     * @param arr 待划分数组
     * @param L 数组的左边起始下标
     * @param R 数组的右边下标
     * @param num 划分标准 （即左边的数都比num小，中间的数都是num，右边的数都比num大）
     * @return 区间（该区间的数都是num）
     */
    private static int[] partition(int[] arr, int L, int R, int num) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < num) {
                //如果当前位置的元素小于num，将当前元素和小于区域的后一个值交换
                // 小于区域范围+1，当前位置+1
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                //如果当前位置的元素大于num，将当前位置的元素与大于区域的前一个位置的元素交换，当前位置不变
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 9, 7, 6, 1, 4, 5};
        int[] res = partition(arr, 0, arr.length - 1, 4);
        String s = Arrays.toString(res);
        System.out.println(s);
    }
}
