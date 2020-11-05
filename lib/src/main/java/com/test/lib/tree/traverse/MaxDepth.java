package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

/**
 * 最大深度
 * <p>
 * 左边的最大深度 与右边的最大深度中大的那个+1
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
