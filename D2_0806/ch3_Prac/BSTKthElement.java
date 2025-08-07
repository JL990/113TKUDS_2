package D2_0806.ch3_Prac;

import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        int size; // 子樹節點總數（包含自己）
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    // 更新節點的 size
    static int getSize(TreeNode node) {
        return node == null ? 0 : node.size;
    }

    static void updateSize(TreeNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    // 插入節點（動態維護 size）
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        updateSize(root);
        return root;
    }

    // 刪除節點
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            // 找到要刪除的節點
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            // 兩個子節點都存在：找右子樹最小值替代
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = delete(root.right, minNode.val);
        }
        updateSize(root);
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 1. 找出第 k 小元素
    public static Integer kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0 || k > getSize(root)) return null;

        int leftSize = getSize(root.left);

        if (k <= leftSize) return kthSmallest(root.left, k);
        else if (k == leftSize + 1) return root.val;
        else return kthSmallest(root.right, k - leftSize - 1);
    }

    // 2. 找出第 k 大元素
    public static Integer kthLargest(TreeNode root, int k) {
        int n = getSize(root);
        return kthSmallest(root, n - k + 1);
    }

    // 3. 找出第 k 小到第 j 小的所有元素
    public static List<Integer> kthRange(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inorderWithRange(root, result, new int[]{0}, k, j);
        return result;
    }

    private static void inorderWithRange(TreeNode node, List<Integer> result, int[] counter, int k, int j) {
        if (node == null) return;

        inorderWithRange(node.left, result, counter, k, j);

        counter[0]++;
        if (counter[0] >= k && counter[0] <= j) {
            result.add(node.val);
        }

        if (counter[0] > j) return;

        inorderWithRange(node.right, result, counter, k, j);
    }

    // 4. 測試用中序列印
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 測試主程式
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) {
            root = insert(root, v);
        }

        System.out.print("中序: ");
        printInOrder(root);
        System.out.println();

        // 1. 第 k 小
        System.out.println("第 3 小元素: " + kthSmallest(root, 3));

        // 2. 第 k 大
        System.out.println("第 2 大元素: " + kthLargest(root, 2));

        // 3. 第 2 小到第 5 小
        System.out.println("第 2 小到第 5 小: " + kthRange(root, 2, 5));

        // 4. 刪除 + 插入後再查詢
        root = delete(root, 25);
        root = insert(root, 12);
        System.out.print("刪除 25 並插入 12 後的中序: ");
        printInOrder(root);
        System.out.println();

        System.out.println("第 3 小元素（更新後）: " + kthSmallest(root, 3));
    }
}
