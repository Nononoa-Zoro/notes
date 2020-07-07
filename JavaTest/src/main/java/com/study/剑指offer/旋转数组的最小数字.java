package com.study.剑指offer;

public class 旋转数组的最小数字 {

    public static void main(String[] args) {
        int[] array = {3,4,5,1,2};
        int res = minNumberInRotateArray(array);
        System.out.println(res);
    }

    public static int minNumberInRotateArray(int [] array) {
        int left = 0;
        int right = array.length-1;
        int mid = left;
        while (array[left]>=array[right]){
            if(right-left==1){
                mid = right;
                break;
            }
            mid = (left+right)/2;
            if(array[mid]>=array[left]){
                left=mid;
            }else if(array[mid]<=array[right]){
                right=mid;
            }
        }
        return mid;
    }
}
