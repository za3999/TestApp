package com.test.lib.divide_and_conquer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 概念
 * <p>
 * 分治算法，根据字面意思解释是“分而治之”，就是把一个复杂的问题分成两个或更多的相同或相似的子问题。
 * 再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解。
 * 原问题的解即子问题的解的合并
 * <p>
 * 算法策略
 * <p>
 * 对于一个规模为 n 的问题，若该问题可以容易地解决（比如说规模 n 较小）则直接解决，
 * 否则将其分解为 k 个规模较小的子问题，这些子问题互相独立且与原问题形式相同，
 * 递归地解这些子问题，然后将各子问题的解合并得到原问题的解。
 * 在平时日常生活中，分治思想也是随处可见的。
 * 例如：当我们打牌时，在进行洗牌时，若牌的数目较多，一个人洗不过来，
 * 则会将牌进行分堆，单独洗一小堆牌是相对容易的，每一堆牌都洗完之后再放到一起，则完成洗牌过程。
 * <p>
 * 使用场景
 * <p>
 * （1）该问题的规模缩小到一定的程度就可以容易地解决。
 * （2）该问题可以分解为若干个规模较小的相同问题，即该问题具有最优子结构性质。
 * （3）利用该问题分解出的子问题的解可以合并为该问题的解。
 * （4）该问题所分解出的各个子问题是相互独立的，即子问题之间不包含公共的子问题。
 * <p>
 * 基本步骤
 * <p>
 * 分治法在每一层递归上都有三个步骤：
 * （1）分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题。
 * （2）求解：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题。
 * （3）合并：将各个子问题的解合并为原问题的解
 * <p>
 * 案例
 * <p>
 * 二分查找
 *   二分查找是典型的分治算法的应用。需要注意的是，二分查找的前提是查找的数列是有序的。
 * <p>
 * 算法流程：
 *   （1）选择一个标志 i 将集合分为二个子集合。
 *   （2）判断标志 L(i) 是否能与要查找的值 des 相等，相等则直接返回。
 *   （3）否则判断 L(i) 与 des 的大小。
 *   （4）基于判断的结果决定下步是向左查找还是向右查找。
 *   （5）递归继续上面的步骤。
 * <p>
 * <p>
 * 全排列问题
 * <p>
 * 问题描述：
 *   有1，2，3，4个数，问你有多少种排列方法，并输出排列。
 * <p>
 * 问题分析：
 * 若采用分治思想进行求解，首先需要把大问题分解成很多的子问题，大问题是所有的排列方法。
 * 那么我们分解得到的小问题就是以 1 开头的排列，以 2 开头的排列，以 3 开头的排列，以 4 开头的排列。
 * 现在这些问题有能继续分解，比如以 1 开头的排列中，只确定了 1 的位置，没有确定 2 ，3 ，4 的位置，
 * 把 2，3，4 三个又看成大问题继续分解，2 做第二个，3 做第二个，或者 4 做第二个。
 * 一直分解下去，直到分解成的子问题只有一个数字的时候，不能再分解。只有一个数的序列只有一种排列方式，则子问题求解容易的多。
 */
public class AA_remark {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        fullSort(arr, 0, arr.length - 1);
        List<List<Integer>> result = fullSort(arr);
        for (List<Integer> integers : result) {
            System.out.println(integers);
        }
    }

    //全排列问题
    public static void fullSort(int[] arr, int start, int end) {
        // 递归终止条件
        if (start == end) {
            for (int i : arr) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            fullSort(arr, start + 1, end);
            swap(arr, i, start);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static List<List<Integer>> fullSort(int[] arr) {
        List<List<Integer>> res = new ArrayList();
        int length = arr.length;
        boolean[] used = new boolean[length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(arr, length, 0, path, used, res);
        return res;
    }

    /**
     * @param arr    输入的数
     * @param length 输入数的长度(冗余,避免一直获取长度)
     * @param deep   遍历深度
     * @param path   走过的路径
     * @param used   当前的元素是否用过
     * @param res    结果集
     */
    private static void dfs(int[] arr, int length, int deep, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (deep == length) {
            res.add(new ArrayList<>(path));
            System.out.println("result:" + path);
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i]) { //如果使用过，忽略
                continue;
            }
            path.addLast(arr[i]);// 加入到路径中
            used[i] = true; //标记当前已经使用
            System.out.println("add:" + path);
            dfs(arr, length, deep + 1, path, used, res); //递归
            path.removeLast(); //从路径中回退
            System.out.println("remove:" + path);
            used[i] = false; // 重置使用状态
        }
    }
}