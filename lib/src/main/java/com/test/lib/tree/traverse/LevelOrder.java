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

    public List<List<Integer>> dfsOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsOrder(root, 0, result);
        return result;
    }

    public void dfsOrder(TreeNode treeNode, int deep, List<List<Integer>> result) {
        if (treeNode == null) {
            return;
        }
        if (deep >= result.size() || result.get(deep) == null) {
            result.add(deep, new ArrayList<>());
        }
        result.get(deep).add(treeNode.val);
        dfsOrder(treeNode.left, deep + 1, result);
        dfsOrder(treeNode.right, deep + 1, result);
    }

    public List<List<Integer>> bfsOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            if (size == 0) {
                break;
            }
            TreeNode treeNode;
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                treeNode = deque.pollFirst();
                array.add(treeNode.val);
                if (treeNode.left != null) {
                    deque.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addLast(treeNode.right);
                }
            }
            result.add(array);
        }
        return result;
    }

}
