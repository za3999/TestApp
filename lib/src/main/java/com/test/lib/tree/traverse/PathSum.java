package com.test.lib.tree.traverse;


/**
 * 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2
 * <p>
 * 分析
 * <p>
 * 退出条件
 * 当左右节点都为null(页节点)时,剩余的值等于当前前节点的值,则存在这样的一条路径
 * 剩余值为前一个节点剩余的值减去前一个节点的值。
 *
 */

import com.test.lib.tree.TreeNode;

public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) {//页节点
            return sum == root.val;
        }
        boolean leftMatch = left == null ? false : hasPathSum(left, sum - root.val); //存在左节节点，计算左节点和剩余值
        boolean rightMatch = right == null ? false : hasPathSum(right, sum - root.val);//存在右节节点，计算右节点和剩余值
        return leftMatch || rightMatch; //左右存在一个这样的值，则满足条件
    }
}
