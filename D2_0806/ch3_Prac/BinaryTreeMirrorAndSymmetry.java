package D2_0806.ch3_Prac;

public class BinaryTreeMirrorAndSymmetry {

    // 二元樹節點定義
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 判斷是否為對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    // 2. 將樹轉為鏡像（直接改變原樹）
    public static void mirrorTree(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
    }

    // 3. 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode a, TreeNode b) {
        return isMirror(a, b);
    }

    // 4. 判斷 subtree 是否為 root 的子樹
    public static boolean isSubtree(TreeNode root, TreeNode subtree) {
        if (root == null) return false;
        if (isIdentical(root, subtree)) return true;
        return isSubtree(root.left, subtree) || isSubtree(root.right, subtree);
    }

    private static boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.val != b.val) return false;
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }

    // 工具函式：中序列印
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // 主程式測試
    public static void main(String[] args) {
        /*
              1
             / \
            2   2
           /     \
          3       3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + (isSymmetric(root) ? "是" : "否"));

        System.out.print("原樹中序: ");
        printInOrder(root);
        System.out.println();

        mirrorTree(root);
        System.out.print("鏡像中序: ");
        printInOrder(root);
        System.out.println();

        // 測試互為鏡像
        TreeNode tree1 = new TreeNode(4);
        tree1.left = new TreeNode(5);
        tree1.right = new TreeNode(6);

        TreeNode tree2 = new TreeNode(4);
        tree2.left = new TreeNode(6);
        tree2.right = new TreeNode(5);

        System.out.println("是否互為鏡像: " + (areMirrors(tree1, tree2) ? "是" : "否"));

        // 測試子樹
        TreeNode sub = new TreeNode(2);
        sub.right = new TreeNode(3);

        System.out.println("是否為子樹: " + (isSubtree(root, sub) ? "是" : "否"));
    }
}
