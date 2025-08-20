package midterm;

import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { this.val = v; }
    }

    /**
     * 建立樹 (層序輸入, -1 表 null)
     * 時間複雜度: O(n)，每個輸入元素最多處理一次
     * 空間複雜度: O(n)，Queue 最多存放 n/2 個節點 (葉子層)
     */
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode node = q.poll();
            if (node == null) continue;

            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 檢查是否為 BST (使用上下界)
     * 時間複雜度: O(n)，每個節點最多訪問一次
     * 空間複雜度: O(h)，h 為樹高，最差 O(n) (退化成鏈表)
     */
    public static boolean isBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    /**
     * 檢查是否為 AVL (後序計算高度 + 檢查平衡因子)
     * 若不是 AVL，回傳 Integer.MIN_VALUE 作為「非法」標記
     *
     * 時間複雜度: O(n)，每個節點計算高度一次
     * 空間複雜度: O(h)，h 為樹高，最差 O(n) (退化成鏈表)
     */
    public static int checkAVL(TreeNode root) {
        if (root == null) return 0;

        int lh = checkAVL(root.left);
        if (lh == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int rh = checkAVL(root.right);
        if (rh == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        if (Math.abs(lh - rh) > 1) return Integer.MIN_VALUE;

        return Math.max(lh, rh) + 1;
    }

    /**
     * 主程式
     * 總時間複雜度: O(n)，因為 buildTree + isBST + checkAVL 各為 O(n)，相加仍為 O(n)
     * 總空間複雜度: O(n)，主要來自建樹 Queue 以及遞迴深度
     */
    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);

        // 先檢查 BST
        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        // 再檢查 AVL
        if (checkAVL(root) == Integer.MIN_VALUE) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }
}
