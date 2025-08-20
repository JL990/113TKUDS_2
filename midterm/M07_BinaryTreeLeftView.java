package midterm;

import java.util.*;

public class M07_BinaryTreeLeftView {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { this.val = v; }
    }

    // 建立二元樹 (層序輸入, -1 表 null)
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode node = q.poll();
            if (node == null) continue;

            // left child
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;

            // right child
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 輸出左視圖
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (i == 0) res.add(cur.val); // 每層的第一個
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreeNode root = buildTree(arr);
        List<Integer> lv = leftView(root);

        System.out.print("LeftView:");
        for (int v : lv) System.out.print(" " + v);
        System.out.println();
    }
}
