package com.test.lib.dynamic_programming;

public class FastPaths {

    public static int getPaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n]; //
        // 初始化
        dp[0][0] = arr[0][0];
        // 初始化最左边的列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 初始化最上边的行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int getPathsV2(int[][] arr) {
        int n = arr[0].length;
        int[] dp = new int[n]; //
        // 初始化最上边的行
        dp[0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + arr[0][i];
        }
        // 推导出 dp[n-1]
        for (int i = 1; i < arr.length; i++) {
            dp[0] = dp[0] + arr[i][0];//j为0的时候直接在累加当前单元格的值
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + arr[i][j];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println("test getPaths:" + getPaths(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println("test getPaths:" + getPathsV2(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }
}
