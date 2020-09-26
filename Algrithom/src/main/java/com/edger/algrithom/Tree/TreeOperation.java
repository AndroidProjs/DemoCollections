package com.edger.algrithom.Tree;

class TreeOperation {
    public static void main(String[] args) {
        Tree tree = new Tree();

        System.out.println();
        System.out.println("Tree pre-order recursive : ");
        tree.preOrderRecursive(tree.getRoot());

        System.out.println();
        System.out.println("Tree pre-order traversal : ");
        tree.preOrderTraversal(tree.getRoot());

        System.out.println();
        System.out.println("Tree in-order recursive : ");
        tree.inOrderRecursive(tree.getRoot());

        System.out.println();
        System.out.println("Tree in-order traversal : ");
        tree.inOrderTraversal(tree.getRoot());

        System.out.println();
        System.out.println("Tree post-order recursive : ");
        tree.postOrderRecursive(tree.getRoot());

        System.out.println();
        System.out.println("Tree post-order traversal : ");
        tree.postOrderTraversal(tree.getRoot());

        System.out.println();
        System.out.println("Tree DFS traversal (up to down) : ");
        tree.dfsUpToDown(tree.getRoot());

        System.out.println();
        System.out.println("Tree DFS traversal (down to up) : ");
        tree.dfsDownToUp(tree.getRoot());

        System.out.println();
        System.out.println("Tree BFS traversal : ");
        tree.bfsTraversal(tree.getRoot());

        System.out.println();
        System.out.println("Tree BFS traversal2 : ");
        tree.bfsTraversal2(tree.getRoot());

        TreePrintUtil.printNode(tree.getRoot());
        System.out.println();
        System.out.println("Tree BFS traversal down to up : ");
        tree.bfsTraversalDownToUp(tree.getRoot());
    }
}
