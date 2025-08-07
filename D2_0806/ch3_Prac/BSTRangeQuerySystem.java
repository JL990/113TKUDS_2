package D2_0806.ch3_Prac;

import java.util.*;

public class BSTRangeQuerySystem {

    // 節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 插入節點（建構 BST）
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    // 1. 範圍查詢：找出 [min, max] 範圍內所有節點
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        dfsRange(root, min, max, result);
        return result;
    }

    private static void dfsRange(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) dfsRange(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) dfsRange(node.right, min, max, result);
    }

    // 2. 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4. 最接近查詢：找出最接近 target 的節點值
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target < root.val) {
                root = root.left;
            } else if (target > root.val) {
                root = root.right;
            } else {
                break; // exact match
            }
        }
        return closest;
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        TreeNode root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        int min = 6, max = 15;

        // 範圍查詢
        List<Integer> inRange = rangeQuery(root, min, max);
        System.out.println("在範圍 [" + min + ", " + max + "] 的節點: " + inRange);

        // 範圍計數
        System.out.println("節點數量: " + rangeCount(root, min, max));

        // 範圍總和
        System.out.println("節點總和: " + rangeSum(root, min, max));

        // 最接近查詢
        int target = 13;
        System.out.println("最接近 " + target + " 的節點值: " + closestValue(root, target));
    }
}
