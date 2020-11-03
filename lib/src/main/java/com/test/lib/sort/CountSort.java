package com.test.lib.sort;

/**
 * 计数排序
 * <p>
 * 时间复杂度 O(n+k)
 * 空间复杂度 O(n)
 * 排序方式 Out-place
 * 稳定性 稳定
 * <p>
 * 算法步骤
 * 花O(n)的时间扫描一下整个序列 A，获取最小值 min 和最大值 max
 * <p>
 * 开辟一块新的空间创建新的数组 B，长度为 ( max – min + 1)
 * <p>
 * 数组 B 中 index 的元素记录的值是 A 中某元素出现的次数
 * <p>
 * 最后输出目标整数序列，具体的逻辑是遍历数组 B，输出相应元素以及对应的个数
 */
public class CountSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = getMaxValue(arr);
        int[] back = new int[max + 1];
        for (int value : arr) {
            back[value]++;
        }
        int sortIndex = 0;
        for (int i = 0; i < max; i++) {
            while (back[i] > 0) {
                arr[sortIndex++] = i;
                back[i]--;
            }
        }
    }

    private static int getMaxValue(int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
