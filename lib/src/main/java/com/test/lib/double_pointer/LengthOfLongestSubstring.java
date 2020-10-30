package com.test.lib.double_pointer;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 思路:双指针
 */

public class LengthOfLongestSubstring {

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

    public static void main(String[] args) {
        System.out.println("result:" + lengthOfLongestSubstring("abcabcbb"));
    }
}
