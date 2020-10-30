package com.test.lib;

public class Util {

    public static void revert(int[] nums, int start, int end) {
        int length = nums.length;
        if (start >= end || start >= length || end >= length) {
            return;
        }
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
