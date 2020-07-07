package com.study.leecode;

import java.util.Arrays;

public class 数组实现一个栈 {


    class ArrayStack {
        int[] arr = new int[10];
        int size;

        public void push(int data) {
            if (size > arr.length) {
                arr = Arrays.copyOf(arr, arr.length * 2);
            }
            arr[size++] = data;
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("栈为空");
            }
            return arr[size - 1];
        }

        public int pop() {
            int data = this.peek();
            size--;
            return data;
        }

        public int max() {
            if (size == 0) {
                throw new RuntimeException("栈为空");
            }
            int res = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                res = Math.max(res, arr[i]);
            }
            return res;
        }

    }

}
