package com.study.leecode;

public class leecode_33搜索旋转排序数组 {
    int[] nums;
    int target;

    //找到最小的数所在的索引
    public int find_rotate_index(int left, int right) {
        if (nums[left] < nums[right]) {
            return 0;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return mid + 1;
            } else {
                if (nums[mid] < nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return 0;
    }

    public int search(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        int n = nums.length;

        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;

        int min = find_rotate_index(0, n - 1);
        if (nums[min] == target) return min;
        if (min == 0) {
            return search(0, n - 1);
        }

        if (target < nums[0]) {
            return search(min, n - 1);
        }
        return search(0, min);


    }

}
