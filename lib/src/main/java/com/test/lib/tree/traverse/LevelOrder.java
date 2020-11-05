package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 层序遍历
 */
public class LevelOrder {

    public List<List<Integer>> order(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        order(root, 0, result);
        return result;
    }

    public void order(TreeNode treeNode, int deep, List<List<Integer>> result) {
        if (treeNode == null) {
            return;
        }
        if (deep >= result.size() || result.get(deep) == null) {
            result.add(deep, new ArrayList<>());
        }
        result.get(deep).add(treeNode.val);
        order(treeNode.left, deep + 1, result);
        order(treeNode.right, deep + 1, result);
    }

    public List<Integer> order(TreeNode root, List<Integer> result) {
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        TreeNode treeNode = deque.pollFirst();
        while (treeNode != null) {
            result.add(treeNode.val);
            if (treeNode.left != null) {
                deque.addLast(treeNode.left);
            }
            if (treeNode.right != null) {
                deque.addLast(treeNode.right);
            }
            treeNode = deque.pollFirst();
        }
        return result;
    }

}
