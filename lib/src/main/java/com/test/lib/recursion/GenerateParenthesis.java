package com.test.lib.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        getParenthesis(res, "", n, n);
        return res;
    }

    private static void getParenthesis(List<String> res, String str, int left, int right) {
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

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        System.out.println(result);
    }
}
