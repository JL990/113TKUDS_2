package D2_0806.ch3_Prac;

import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 所有根到葉路徑
    public static List<String> allRootToLeafPaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        buildPaths(root, "", result);
        return result;
    }

    private static void buildPaths(TreeNode node, String path, List<String> result) {
        if (node == null) return;
        path += node.val;
        if (node.left == null && node.right == null) {
            result.add(path);
        } else {
            path += "->";
            buildPaths(node.left, path, result);
            buildPaths(node.right, path, result);
        }
    }

    // 2. 是否存在根到葉路徑總和為目標值
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 最大根到葉總和
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 4. 任意兩節點間最大路徑總和（樹的直徑）
    private static int maxPathSum = Integer.MIN_VALUE;

    public static int maxAnyNodePathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxPathSum;
    }

    private static int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxPathSum = Math.max(maxPathSum, left + right + node.val);
        return node.val + Math.max(left, right);
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
               10
              /  \
             5    -3
            / \     \
           3   2     11
          / \   \
         3  -2   1
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("所有根到葉節點的路徑:");
        List<String> paths = allRootToLeafPaths(root);
        for (String path : paths) {
            System.out.println(path);
        }

        int targetSum = 18;
        System.out.println("\n是否存在根到葉路徑總和為 " + targetSum + ": " + hasPathSum(root, targetSum));

        System.out.println("\n最大根到葉總和: " + maxRootToLeafSum(root));

        System.out.println("\n任意兩節點間最大路徑總和（樹的直徑）: " + maxAnyNodePathSum(root));
    }
}
