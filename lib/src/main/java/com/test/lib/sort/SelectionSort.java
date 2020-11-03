package com.test.lib.sort;

import com.test.lib.Util;

/**
 * 选择排序
 * 时间复杂度 O(n2)
 * 空间复杂度 O(1)
 * 排序方式 In-place
 * 稳定性 不稳定
 * <p>
 * 算法步骤
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * <p>
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * <p>
 * 重复第二步，直到所有元素均排序完毕。
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            Util.swap(arr, i, minIndex);
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
