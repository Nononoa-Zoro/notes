package com.study.sort;
/**
 * 堆是一个完全二叉树
 * 完全二叉树的概念：
 * 1.一棵树，除了最后一行，它是一课满二叉树
 * 2.或者，如果一个节点只有左孩子，那么这个节点之后的所有节点都是叶子节点
 * 堆排序的算法思想
 * 当前节点i的左孩子2*i+1 右孩子2*i+2
 * 当前节点的父节点(i-1)/2
 *
 * 1. 构造一个堆 因为堆结构不是全局有序的 但是堆顶元素是最大的
 * 2. 输出一个最大值或是最小值以后 如何调整堆结构
 *
 * 堆排序不是稳定的排序算法
 */
public class 堆排序 {

    public static void main(String[] args) {
        int[] arr = {5,2,3,6,7,9,1};
        heapSort(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        //一边插入一边检查 当前节点的如果大于父节点就上浮
        for (int i = 0; i < arr.length; i++) {
            insert(arr, i);
        }
        int size = arr.length;
        //把数组的0位置和最后一个位置交换 然后开始调整 因为可能破坏了堆结构
        swap(arr, 0, --size);
        while (size > 0) {
            modify(arr, 0, size);
            swap(arr, 0, --size);
        }

    }

    public static void insert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    public static void modify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int right = left + 1;
            int largest = right < size && arr[right] > arr[left] ? right : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (index == largest) break;
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
