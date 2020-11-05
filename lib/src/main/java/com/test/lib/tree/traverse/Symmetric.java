package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 分析
 * <p>
 * 只要判断左树和右树对称就证明是对称
 * 如何判断左树和右树对称呢？
 * 左树的左节点和右树的右节点对称，左树的右节点和右树的左接点对称，则左右树对称
 */
public class Symmetric {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isChildSymmetric(root.left, root.right);
    }

    private boolean isChildSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isChildSymmetric(left.left, right.right) && isChildSymmetric(left.right, right.left);
    }
}
