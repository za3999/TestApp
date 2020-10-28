package com.test.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //无重复的最长子串
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        int right = -1;
        int length = s.length();
        Set<Character> exist = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                exist.remove(s.charAt(i - 1));
            }
            while (right + 1 < length && !exist.contains(s.charAt(right + 1))) {
                exist.add(s.charAt(right + 1));
                right++;
            }
            result = Math.max(result, right - i + 1);
        }
        return result;
    }

    //装最多的水（双指针）
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        int temp;
        while (left < right) {
            temp = (right - left) * Math.min(height[left], height[right]);
            result = Math.max(temp, result);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    //三数相加
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    //删除链表的倒数第N个节点（只遍历一次）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        getParenthesis(res, "", n, n);
        return res;
    }

    private void getParenthesis(List<String> res, String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left == right) {
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(res, str + "(", left - 1, right);
        } else if (left < right) {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if (left > 0) {
                getParenthesis(res, str + "(", left - 1, right);
            }
            getParenthesis(res, str + ")", left, right - 1);
        }
    }

    //两数相除
    public static int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0;
        }
        boolean negative = false;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            negative = true;
        }
        long dividendL = dividend;
        long divisorL = divisor;
        int result = div(Math.abs(dividendL), Math.abs(divisorL));
        if (!negative && result == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return negative ? result > 0 ? -result : result : Math.abs(result);
    }

    static int div(long a, long b) {
        if (a < b) return 0;
        int count = 1;
        long tb = b;
        while (tb + tb <= a) {
            count = count + count;
            tb = tb + tb;
        }
        return count + div(a - tb, b);
    }

    public static void main(String[] args) {
        System.out.println("test:" + divide(-2147483648, -1));
    }
}