package com.test.lib.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 桶排序
 * <p>
 * 时间复杂度 O(n+k)
 * 空间复杂度 O(n+k)
 * 排序方式 Out-place
 * 稳定性 不稳定
 * <p>
 * 算法步骤
 * 设置固定数量的空桶。
 * <p>
 * 把数据放到对应的桶中。
 * <p>
 * 对每个不为空的桶中数据进行排序。
 * <p>
 * 拼接不为空的桶中数据，得到结果
 */
public class BucketSort {

    public static void sort(int[] arr, int bucketSize) {
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
            if (minValue > value) {
                minValue = value;
            }
        }
        int bucketNumber = (maxValue - minValue) / bucketSize + 1; //除法有余数,所以+1
        List<Integer>[] bucket = new ArrayList[bucketSize];
        for (int i = 0; i < arr.length; i++) {
            int temp = (arr[i] - minValue) / bucketNumber;
            if (bucket[temp] == null) {
                bucket[temp] = new ArrayList<>();
            }
            bucket[temp].add(arr[i]);
        }

        for (int j = 0; j < bucketSize; j++) {
            if (bucket[j] == null) {
                continue;
            }
            Collections.sort(bucket[j]);
        }

        int sortIndex = 0;
        for (int j = 0; j < bucketSize; j++) {
            if (bucket[j] == null) {
                continue;
            }
            for (int number : bucket[j]) {
                arr[sortIndex++] = number;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};
        sort(arr, 10);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
