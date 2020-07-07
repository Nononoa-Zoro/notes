package com.study.leecode;

public class leecode_55跳跃游戏_动态规划 {
    enum Index {
        GOOD, BAD
    }


    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];

        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.BAD;
        }
        //最后一个位置是可以到自己的
        memo[memo.length - 1] = Index.GOOD;

        //从后往前开始遍历 如果在当前位置的最大步长范围内可以到最后一个位置 那么当前位置就是GOOD
        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestStep = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestStep; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }
}
