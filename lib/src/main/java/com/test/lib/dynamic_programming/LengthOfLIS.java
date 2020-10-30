package com.test.lib.dynamic_programming;

/**
 * 最长升序，动态规划
 */
public class LengthOfLIS {

    public static int lengthOfLIS(int[] nums) {
        int[] array = new int[nums.length];
        int length = nums.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            int temp = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    temp = Math.max(temp, array[j] + 1);
                }
            }
            array[i] = temp;
            max = Math.max(max, temp);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {50, 1, 10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("test:" + lengthOfLIS(array));
    }
}
