package D2_0806.ch3_Prac;

import java.util.*;

public class BinaryTreeBasicOperations {

    // 節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 計算節點總和與平均值
    static class SumResult {
        int sum = 0;
        int count = 0;
    }

    public static SumResult calculateSumAndCount(TreeNode root) {
        SumResult result = new SumResult();
        dfsSum(root, result);
        return result;
    }

    private static void dfsSum(TreeNode node, SumResult result) {
        if (node == null) return;
        result.sum += node.val;
        result.count++;
        dfsSum(node.left, result);
        dfsSum(node.right, result);
    }

    // 2. 找出最大值與最小值節點
    public static int findMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(findMax(root.left), findMax(root.right)));
    }

    public static int findMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(findMin(root.left), findMin(root.right)));
    }

    // 3. 計算樹的寬度（每層最多節點數）
    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹（Complete Binary Tree）
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                end = true;
            } else {
                if (end) return false;  // 如果之前出現過 null，後面又有節點 => 非完全樹
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }

        return true;
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
              10
             /  \
            5    15
           / \     \
          3   7     18
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        // 1. 節點總和與平均值
        SumResult result = calculateSumAndCount(root);
        System.out.println("節點總和: " + result.sum);
        System.out.printf("節點平均值: %.2f%n", (double) result.sum / result.count);

        // 2. 最大與最小值節點
        System.out.println("最大節點值: " + findMax(root));
        System.out.println("最小節點值: " + findMin(root));

        // 3. 樹的最大寬度
        System.out.println("樹的最大寬度: " + treeWidth(root));

        // 4. 是否為完全二元樹
        System.out.println("是否為完全二元樹: " + (isCompleteBinaryTree(root) ? "是" : "否"));
    }
}
