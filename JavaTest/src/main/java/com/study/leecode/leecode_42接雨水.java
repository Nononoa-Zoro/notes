package com.study.leecode;

import java.util.Stack;

public class leecode_42接雨水 {

    public static int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int last = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int t = stack.pop();
                res += (i - t - 1) * (height[t] - last);
                last = height[t];
            }
            if (!stack.isEmpty()) res += (i - stack.peek() - 1) * (height[i] - last);
            stack.push(i);

        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = trap(height);
        System.out.println(res);
    }
}
