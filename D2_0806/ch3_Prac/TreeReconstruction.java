package D2_0806.ch3_Prac;

import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 根據前序和中序重建二元樹
    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inIndex.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inIndex);
    }

    private static TreeNode buildPreIn(int[] pre, int ps, int pe,
                                       int[] in, int is, int ie,
                                       Map<Integer, Integer> inIndex) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int ri = inIndex.get(pre[ps]);
        int leftSize = ri - is;
        root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, inIndex);
        root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, inIndex);
        return root;
    }

    // 2. 根據後序和中序重建二元樹
    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inIndex.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length - 1,
                           inorder, 0, inorder.length - 1, inIndex);
    }

    private static TreeNode buildPostIn(int[] post, int ps, int pe,
                                        int[] in, int is, int ie,
                                        Map<Integer, Integer> inIndex) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(post[pe]);
        int ri = inIndex.get(post[pe]);
        int leftSize = ri - is;
        root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, inIndex);
        root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, inIndex);
        return root;
    }

    // 3. 根據層序走訪建立完全二元樹
    public static TreeNode buildCompleteBinaryTree(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode curr = queue.poll();
            if (i < levelOrder.length) {
                curr.left = new TreeNode(levelOrder[i++]);
                queue.offer(curr.left);
            }
            if (i < levelOrder.length) {
                curr.right = new TreeNode(levelOrder[i++]);
                queue.offer(curr.right);
            }
        }
        return root;
    }

    // 4. 驗證：印出三種走訪結果
    public static void printPreOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public static void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }

    public static void printPostOrder(TreeNode root) {
        if (root != null) {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            System.out.print(curr.val + " ");
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        System.out.println("從前序+中序重建:");
        TreeNode root1 = buildTreeFromPreIn(preorder, inorder);
        printPreOrder(root1); System.out.println(" ← Preorder");
        printInOrder(root1); System.out.println(" ← Inorder");
        printPostOrder(root1); System.out.println(" ← Postorder");

        System.out.println("\n從後序+中序重建:");
        TreeNode root2 = buildTreeFromPostIn(postorder, inorder);
        printPreOrder(root2); System.out.println(" ← Preorder");
        printInOrder(root2); System.out.println(" ← Inorder");
        printPostOrder(root2); System.out.println(" ← Postorder");

        System.out.println("\n從層序重建完全二元樹:");
        TreeNode root3 = buildCompleteBinaryTree(levelOrder);
        printLevelOrder(root3); System.out.println(" ← LevelOrder");
        printInOrder(root3); System.out.println(" ← Inorder");
    }
}
