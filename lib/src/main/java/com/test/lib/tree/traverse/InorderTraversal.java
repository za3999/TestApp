package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.List;


/**
 * 中序遍历
 */
public class InorderTraversal implements ITraverse {

    @Override
    public void order(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return;
        }
        order(treeNode.left, result);
        result.add(treeNode.val);
        order(treeNode.right, result);
    }
}
