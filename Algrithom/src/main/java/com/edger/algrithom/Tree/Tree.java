package com.edger.algrithom.Tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Tree {
    private TreeNode root;

    {
        /*
       树的结构示例：
                  16
              /      \
             13      20
           /   \        \
          10   15        20
                       /   \
                     21     26
       */

        root = new TreeNode(16);

        root.left = new TreeNode(13);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(15);

        root.right = new TreeNode(20);
        root.right.right = new TreeNode(22);
        root.right.right.left = new TreeNode(21);
        root.right.right.right = new TreeNode(26);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void preOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        System.out.print(" ");
        preOrderRecursive((TreeNode) root.left);
        preOrderRecursive((TreeNode) root.right);
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            result.add(node.val);
            if (node.right != null) {
                stack.offerFirst((TreeNode) node.right);
            }
            if (node.left != null) {
                stack.offerFirst((TreeNode) node.left);
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public void inOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecursive((TreeNode) root.left);
        System.out.print(root.val);
        System.out.print(" ");
        inOrderRecursive((TreeNode) root.right);
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.offerFirst(node);
                node = (TreeNode) node.left;
            }
            node = stack.pollFirst();
            result.add(node.val);
            node = (TreeNode) node.right;
        }

        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderRecursive((TreeNode) root.left);
        postOrderRecursive((TreeNode) root.right);
        System.out.print(root.val);
        System.out.print(" ");
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();

        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            result.addFirst(node.val);
            if (node.left != null) {
                stack.offerFirst((TreeNode) node.left);
            }
            if (node.right != null) {
                stack.offerFirst((TreeNode) node.right);
            }
        }

        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<Integer> dfsUpToDown(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        dfs(root, result);
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        dfs((TreeNode) node.left, result);
        dfs((TreeNode) node.right, result);
    }

    public List<Integer> dfsDownToUp(TreeNode root) {
        return divideAndConquer(root);
    }

    private List<Integer> divideAndConquer(TreeNode node) {
        if (node == null) {
            return null;
        }
        List<Integer> result = new LinkedList<>();

        // 分治
        List<Integer> left = divideAndConquer((TreeNode) node.left);
        List<Integer> right = divideAndConquer((TreeNode) node.right);
        // 合并结果
        result.add(node.val);
        if (left != null) {
            result.addAll(left);
        }
        if (right != null) {
            result.addAll(right);
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<Integer> bfsTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            result.add(current.val);
            if (current.left != null) {
                queue.offer((TreeNode) current.left);
            }
            if (current.right != null) {
                queue.offer((TreeNode) current.right);
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<Integer> bfsTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // size 记录当前层有多少元素 (遍历当前层，再添加下一层)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                result.add(node.val);
                if (node.left != null) {
                    queue.add((TreeNode) node.left);
                }
                if (node.right != null) {
                    queue.add((TreeNode) node.right);
                }
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    public List<List<Integer>> bfsTraversalDownToUp(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<Integer> currentLevelResult = new LinkedList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                currentLevelResult.add(node.val);
                if (node.left != null) {
                    queue.offer((TreeNode) node.left);
                }
                if (node.right != null) {
                    queue.offer((TreeNode) node.right);
                }
            }
            result.addFirst(currentLevelResult);
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

}
