package com.test.lib.sort;

import com.test.lib.Util;

/**
 * 冒泡排序
 * <p>
 * 时间复杂度 O(n2)
 * 空间复杂度 O(1)
 * 排序方式 In-place
 * 稳定性 稳定
 * <p>
 * 算法步骤
 * <p>
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * <p>
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * <p>
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * <p>
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Util.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
