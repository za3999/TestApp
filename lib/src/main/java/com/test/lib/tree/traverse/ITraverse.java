package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;


public interface ITraverse {

    default List<Integer> order(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        order(root, result);
        return result;
    }

    void order(TreeNode treeNode, List<Integer> result);


}
