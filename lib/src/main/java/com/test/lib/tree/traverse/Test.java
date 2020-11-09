package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

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

        List<List<Integer>> result = new LevelOrder().dfsOrder(treeNode);
        System.out.print("层次遍历(深度优先):");
        for (List<Integer> integers : result) {
            System.out.print("[");
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("]");
        }
        System.out.println();
       result = new LevelOrder().bfsOrder(treeNode);
        System.out.print("层次遍历(广度优先):");
        for (List<Integer> integers : result) {
            System.out.print("[");
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.print("]");
        }
        System.out.println();
        System.out.println("最大深度："+new MaxDepth().maxDepth(treeNode));
    }
}

