package com.test.lib;

import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        int size = nums.length;
        int revert = 0;
        for (int i = size - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {//找到第一个前面的数字比后面的数字大的位置（查打过的前面的都比后面的小，是一个降序）
                revert = i;//标记翻转的位置（因为i以后的数为一个降序队列，所以需要翻转过来。尾数才是最小值）
                for (int j = i; j < size; j++) {//从查找到的位置往队尾查找
                    if (nums[i - 1] >= nums[j]) { //找到第一个比查找到的位置数字小的值
                        swap(nums, i - 1, j - 1);//将查找到的位置的数字和这个值的前一个数字交换
                        break;
                    } else if (j == size - 1) {//如果已经是最后一个值了
                        swap(nums, i - 1, j);//和最后一个值交换
                    }
                }
                break;
            }
        }
        revert(nums, revert, nums.length - 1);//翻转后面的数据
    }

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

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 1};
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
        nextPermutation(input);
        System.out.print("after ");
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
    }

}
