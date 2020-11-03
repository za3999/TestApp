package com.test.lib.sort;

import com.test.lib.Util;

/**
 * 希尔排序(对插入排序的优化)
 * 时间复杂度 O(nlogn)
 * 空间复杂度 O(1)
 * 排序方式 In-place
 * 稳定性 不稳定
 * <p>
 * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
 * <p>
 * 按增量序列个数 k，对序列进行 k 趟排序；
 * <p>
 * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序。
 * 仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
 */

public class ShellSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int gap = arr.length / 2;
        while (gap > 0) {
            sort(arr, gap);
            gap = gap / 2;
        }
    }

    private static void sort(int[] arr, int gap) {//间隔gap,插入排序
        for (int i = gap; i < arr.length; i++) {
            int j = i;
            while (j >= gap && arr[j] < arr[j - gap]) {
                Util.swap(arr, j, j - gap);
                j -= gap;
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
