package com.test.lib.sort;

import com.test.lib.Util;

/**
 * 插入排序
 * <p>
 * 时间复杂度 O(n2)
 * 空间复杂度 O(1)
 * 排序方式 In-place
 * 稳定性 稳定
 * <p>
 * 算法步骤
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * <p>
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 * 如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。
 */
public class InsertSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                Util.swap(arr, j - 1, j);
                j--;
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
