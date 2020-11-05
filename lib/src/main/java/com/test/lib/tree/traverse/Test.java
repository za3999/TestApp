package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(2);
        treeNode.right.left = new TreeNode(3);

        System.out.println("前序遍历:" + new PreTraverse().order(treeNode));

        System.out.println("中序遍历:" + new InorderTraversal().order(treeNode));

        System.out.println("后序遍历:" + new PostTraverse().order(treeNode));

        //  [3, 9, 20, null, null, 15, 7]
        treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.left.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);

        List<List<Integer>> result = new LevelOrder().order(treeNode);
        System.out.print("层次遍历(带深度):");
        for (List<Integer> integers : result) {
            System.out.print("[");
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("]");
        }
        System.out.print("\n层次遍历(不带深度):");
        System.out.println(new LevelOrder().order(treeNode, new ArrayList<>()));
        System.out.println(new MaxDepth().maxDepth(treeNode));
    }
}

