package com.test.lib;

public class JavaClass {

    //阶乘最后的0的个数
    public static long trailingZeros(long n) {
        long count = 0;
        long i = 5;
        while (i <= n) {
            count += i / 5;
            i = i * 5;
        }
        return count;
    }

    //两数之和
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = nums[i];
                    result[1] = nums[j];
                    return result;
                }
            }
        }
        return result;
    }

    //最长升序，动态规划
    public static int lengthOfLIS(int[] nums) {
        int[] array = new int[nums.length];
        int length = nums.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            int temp = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    temp = Math.max(temp, array[j] + 1);
                }
            }
            array[i] = temp;
            max = Math.max(max, temp);
        }
        return max;
    }

    //矩阵翻转
    public static int[][] transposeMatrix(int[][] A) {
        int iL = A.length;
        int jL = A[0].length;
        int[][] B = new int[jL][iL];
        for (int j = 0; j < jL; j++) {
            for (int i = 0; i < iL; i++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    //最长回字串（耗时）
    public static String longestPalindrome(String s) {
        int length = s.length();
        int max = 0;
        String result = "";
        int k;
        if (length == 1) {
            return s;
        }
        for (int i = 0; i < length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    k = (i - j) / 2;
                    boolean match = true;
                    for (int step = 0; step <= k; step++) {
                        if (i - step <= j + step) {
                            break;
                        }
                        if (s.charAt(i - step) != s.charAt(j + step)) {
                            match = false;
                            break;
                        }
                    }
                    if (match && max < i - j + 1) {
                        max = i - j + 1;
                        result = s.substring(j, i + 1);
                        break;
                    }
                } else if (max < 1) {
                    max = 1;
                    result = s.charAt(i) + "";
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "acaa";
        System.out.println("test:" + longestPalindrome(s));
    }
}