package com.edger.algrithom.Tree;

class LowestCommonAncestor {
    private static boolean isFound = false;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        TreeNode p = (TreeNode) root.left.right.left;
        root.left.right.right = new TreeNode(4);
        TreeNode q = (TreeNode) root.left.right.right;
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreePrintUtil.printNode(root);
        System.out.println();
        TreeNode result = findLowestCommonAncestor(root, p, q);
        if (result == null) {
            System.out.println("not found.");
        } else {
            System.out.println(result.val);
        }
    }

    private static TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = findLowestCommonAncestor((TreeNode) root.left, p, q);
        if (isFound) {
            return left;
        }
        TreeNode right = findLowestCommonAncestor((TreeNode) root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        isFound = true;
        return root;
    }
}
