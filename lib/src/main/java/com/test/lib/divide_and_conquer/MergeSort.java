package com.test.lib.divide_and_conquer;

/**
 * 归并排序
 * 时间复杂度 O(nlogn)
 * 空间复杂度 O(n)
 * 排序方式 Out-place
 * 稳定性 稳定
 *
 * <p>
 * 归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。
 * 然后再把有序子序列合并为整体有序序列。即先划分为两个部分，最后进行合并。
 */
public class MergeSort {

    public static void sort(int[] arr) {
        int[] tmp = new int[arr.length];//辅助数组
        sort(arr, 0, arr.length - 1, tmp);
    }

    private static void sort(int[] arr, int start, int end, int[] tmp) {
        if (start >= end) {//当子序列中只有一个元素时结束递归
            return;
        }
        int mid = (start + end) / 2;//划分子序列
        sort(arr, start, mid, tmp);//对左侧子序列进行递归排序
        sort(arr, mid + 1, end, tmp);//对右侧子序列进行递归排序
        merge(arr, start, mid, end, tmp);//合并
    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] <= arr[p2]) {
                tmp[k++] = arr[p1++];
            } else {
                tmp[k++] = arr[p2++];
            }
        }

        while (p1 <= mid) tmp[k++] = arr[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) tmp[k++] = arr[p2++];//同上
        //复制回原素组
        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i];
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
