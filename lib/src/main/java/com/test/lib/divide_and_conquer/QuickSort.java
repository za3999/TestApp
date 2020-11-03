package com.test.lib.divide_and_conquer;

import com.test.lib.Util;

/**
 * 快速排序
 * <p>
 * 时间复杂度 O(nlogn)
 * 空间复杂度 O(logn)
 * 排序方式 In-place
 * 稳定性 不稳定
 * <p>
 * 快速排序的基本思想：当前待排序的无序区为 A[low..high] ，利用分治法可将快速排序的基本思想描述为：
 * （1）分解：
 * 在A[low..high]中任选一个记录作为基准(pivot)，以此基准将当前无序区划分为左、右两个较小的子区间R[low..pivotpos-1) 和 R[pivotpos+1..high]
 * 并使左边子区间中所有记录的关键字均小于等于基准记录(不妨记为pivot)的关键字 pivot.key，右边的子区间中所有记录的关键字均大于等于pivot.key，而基准记录pivot则位于正确的位置( pivotpos )上，它无须参加后续的排序。
 * <p>
 * （2）求解：
 * 通过递归调用快速排序对左、右子区间R[low..pivotpos-1]和R[pivotpos+1..high]快速排序。
 * （3）合并：
 * 因为当”求解”步骤中的两个递归调用结束时，其左、右两个子区间已有序。对快速排序而言，”组合”步骤无须做什么，可看作是空操作。
 */
public class QuickSort {

    public static void sort(int[] arr, int low, int high) {
        if (high <= low) return;
        int i = low;
        int j = high + 1;
        int key = arr[low];
        while (true) {
            /*从左向右找比key大的值*/
            while (arr[++i] < key) {
                if (i == high) {
                    break;
                }
            }
            /*从右向左找比key小的值*/
            while (arr[--j] > key) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) break;
            Util.swap(arr, i, j);
        }
        Util.swap(arr, low, j);
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};
        sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
