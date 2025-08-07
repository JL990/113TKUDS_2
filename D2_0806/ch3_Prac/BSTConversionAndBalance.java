package D2_0806.ch3_Prac;

import java.util.*;

public class BSTConversionAndBalance {

    // Tree Node 定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // ===============================
    // 1. BST → 排序雙向鏈結串列（in-order）
    static class DLLWrapper {
        TreeNode head = null, prev = null;
    }

    public static TreeNode bstToDoublyLinkedList(TreeNode root) {
        DLLWrapper wrapper = new DLLWrapper();
        convertToDLL(root, wrapper);
        return wrapper.head;
    }

    private static void convertToDLL(TreeNode node, DLLWrapper wrapper) {
        if (node == null) return;
        convertToDLL(node.left, wrapper);
        if (wrapper.prev == null) {
            wrapper.head = node;
        } else {
            wrapper.prev.right = node;
            node.left = wrapper.prev;
        }
        wrapper.prev = node;
        convertToDLL(node.right, wrapper);
    }

    public static void printDoublyLinkedList(TreeNode head) {
        TreeNode curr = head;
        System.out.print("Doubly Linked List: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    // ===============================
    // 2. 排序陣列 → 平衡 BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, left, mid - 1);
        node.right = buildBST(nums, mid + 1, right);
        return node;
    }

    // ===============================
    // 3. 檢查是否平衡 + 計算平衡因子
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int left = checkBalance(node.left);
        int right = checkBalance(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    public static void printBalanceFactors(TreeNode root) {
        if (root == null) return;
        int left = height(root.left);
        int right = height(root.right);
        int bf = left - right;
        printBalanceFactors(root.left);
        System.out.println("Node " + root.val + " - Balance Factor: " + bf);
        printBalanceFactors(root.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ===============================
    // 4. 節點值改為「大於等於該節點值的總和」
    public static void convertToGreaterSumTree(TreeNode root) {
        int[] sum = new int[1]; // 使用陣列包裝整數
        reverseInOrderSum(root, sum);
    }

    private static void reverseInOrderSum(TreeNode node, int[] sum) {
        if (node == null) return;
        reverseInOrderSum(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        reverseInOrderSum(node.left, sum);
    }

    // ===============================
    // 輔助：中序走訪印出 BST
    public static void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }

    // ===============================
    // 測試主程式
    public static void main(String[] args) {
        // 測試資料
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        TreeNode bst = sortedArrayToBST(sorted);
        System.out.print("中序遍歷 (建好後): ");
        printInOrder(bst);
        System.out.println();

        // 1. 轉成雙向鏈結串列
        TreeNode dllHead = bstToDoublyLinkedList(bst);
        printDoublyLinkedList(dllHead);

        // 2. 再次建立 BST 用於接下來測試（因為前面 DLL 操作破壞了）
        bst = sortedArrayToBST(sorted);

        // 3. 檢查是否平衡並列出平衡因子
        System.out.println("是否平衡: " + isBalanced(bst));
        printBalanceFactors(bst);

        // 4. 轉換為 Greater Sum Tree
        convertToGreaterSumTree(bst);
        System.out.print("中序遍歷 (Greater Sum Tree): ");
        printInOrder(bst);
        System.out.println();
    }
}
