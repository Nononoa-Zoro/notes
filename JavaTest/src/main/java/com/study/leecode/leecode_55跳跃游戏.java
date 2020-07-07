package com.study.leecode;

public class leecode_55跳跃游戏 {
    public boolean canJump(int[] nums) {
        return dfs(0, nums);
    }

    public boolean dfs(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(nums[position]+position, nums.length - 1);


        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
            if (dfs(nextPosition, nums)) {
                return true;
            }

        }
        return false;

    }

}
