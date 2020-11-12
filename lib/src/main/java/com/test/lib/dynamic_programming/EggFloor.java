package com.test.lib.dynamic_programming;

/**
 * 高楼扔鸡蛋
 * <p>
 * 若干层楼，若干个鸡蛋，算出最少的尝试次数，找到鸡蛋恰好摔不碎的那层楼。
 */

import java.util.HashMap;

public class EggFloor {

    public static int getCount(int egg, int floor, HashMap<String, Integer> dict) {
        String key = egg + "_" + floor;
        int res = 0;
        if (egg <= 1) return floor;
        if (floor <= 0) return 0;
        //备忘录,避免重复计算
        if (dict.containsKey(key)) {
            return dict.get(key);
        }
//        //常规搜索
//        for (int i = 1; i <= floor; i++) {
//            res = Math.min(res, Math.max(
//                    getCount(egg - 1, i - 1, dict)//碎
//                    , getCount(egg, floor - i, dict) //没碎
//                    ) + 1 // 在第 i 楼扔了一次
//            );
//        }
        // 用二分搜索代替线性搜索
        int lo = 1, hi = floor;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int broken = getCount(egg - 1, mid - 1, dict);
            int notBroken = getCount(egg, floor - mid, dict);
            if (broken > notBroken) {
                hi = mid - 1;
                res = Math.min(res, broken + 1) + 1;
            } else {
                lo = mid + 1;
                res = Math.min(res, notBroken + 1) + 1;
            }
        }
        dict.put(key, res);
        return res;
    }

    public static int superEggDrop(int K, int N) {
        // m 最多不会超过 N 次（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        // Java 默认初始化数组都为 0
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println("test:" + getCount(2, 200, new HashMap<>()));
        System.out.println("test:" + superEggDrop(2, 200));
    }
}
