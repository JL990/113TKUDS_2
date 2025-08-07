package D2_0806.ch3_Prac;

import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 驗證是否為有效 BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // 2. 找出 BST 中錯誤的節點（中序走訪中不遞增的節點對）
    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        TreeNode[] prev = new TreeNode[1];
        findInvalidNodesInOrder(root, prev, result);
        return result;
    }

    private static void findInvalidNodesInOrder(TreeNode node, TreeNode[] prev, List<TreeNode> result) {
        if (node == null) return;
        findInvalidNodesInOrder(node.left, prev, result);
        if (prev[0] != null && node.val < prev[0].val) {
            result.add(prev[0]);
            result.add(node);
        }
        prev[0] = node;
        findInvalidNodesInOrder(node.right, prev, result);
    }

    // 3. 修復兩個節點位置錯誤的 BST
    public static void recoverTree(TreeNode root) {
        TreeNode[] nodes = new TreeNode[3]; // [first, second, prev]
        recoverInOrder(root, nodes);
        if (nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }

    private static void recoverInOrder(TreeNode node, TreeNode[] nodes) {
        if (node == null) return;
        recoverInOrder(node.left, nodes);

        if (nodes[2] != null && node.val < nodes[2].val) {
            if (nodes[0] == null) {
                nodes[0] = nodes[2];
                nodes[1] = node;
            } else {
                nodes[1] = node;
            }
        }
        nodes[2] = node;

        recoverInOrder(node.right, nodes);
    }

    // 4. 計算需移除多少節點使其成為有效 BST
    public static int minDeletionsToMakeValidBST(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inOrderValues(root, inorder);
        return inorder.size() - lengthOfLIS(inorder);
    }

    private static void inOrderValues(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrderValues(node.left, list);
        list.add(node.val);
        inOrderValues(node.right, list);
    }

    // 最長遞增子序列（Longest Increasing Subsequence）
    private static int lengthOfLIS(List<Integer> nums) {
        List<Integer> dp = new ArrayList<>();
        for (int num : nums) {
            int i = Collections.binarySearch(dp, num);
            if (i < 0) i = -i - 1;
            if (i == dp.size()) dp.add(num);
            else dp.set(i, num);
        }
        return dp.size();
    }

    // 中序印出 BST
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
            錯誤 BST 範例（3 和 1 互換）：
                    2
                   / \
                  3   4
                 /
                1
        */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(4);

        System.out.print("原始中序：");
        printInOrder(root);
        System.out.println();

        System.out.println("是否為有效 BST: " + isValidBST(root));

        List<TreeNode> invalid = findInvalidNodes(root);
        System.out.print("錯誤節點: ");
        for (TreeNode n : invalid) System.out.print(n.val + " ");
        System.out.println();

        recoverTree(root);
        System.out.print("修復後中序：");
        printInOrder(root);
        System.out.println();

        System.out.println("是否為有效 BST: " + isValidBST(root));

        // 測試需移除多少節點才能成為 BST（用中序非遞增節點）
        TreeNode badRoot = new TreeNode(10);
        badRoot.left = new TreeNode(5);
        badRoot.right = new TreeNode(2);  // 錯誤位置
        System.out.println("最少移除節點數以變成 BST: " + minDeletionsToMakeValidBST(badRoot));
    }
}
