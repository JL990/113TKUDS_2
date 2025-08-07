package D2_0806.ch3_Prac;

import java.util.*;

public class LevelOrderTraversalVariations {

    // 定義樹的節點結構
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 1. 每一層的節點存在不同 List 中
    public static List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(level);
        }
        return result;
    }

    // 2. 之字形層序走訪（Zigzag Level Order）
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(level);
            leftToRight = !leftToRight;
        }

        return result;
    }

    // 3. 每層的最後一個節點
    public static List<Integer> rightmostEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode node = null;

            for (int i = 0; i < size; i++) {
                node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            // 最後一個 node 就是這層的最後一個節點
            if (node != null) result.add(node.val);
        }

        return result;
    }

    // 4. 垂直層序走訪（Vertical Order）
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap 用來自動排序水平位置
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode node = p.node;
            int col = p.col;

            map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);

            if (node.left != null) q.offer(new Pair(node.left, col - 1));
            if (node.right != null) q.offer(new Pair(node.right, col + 1));
        }

        result.addAll(map.values());
        return result;
    }

    // 輔助類別：儲存節點與其水平位置
    static class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        /*
            測試樹：
                   1
                 /   \
                2     3
               / \   / \
              4   5 6   7
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // 1. 每一層各一 List
        System.out.println("每層分開:");
        printListOfLists(levelOrderByLevel(root));

        // 2. 之字形走訪
        System.out.println("之字形層序:");
        printListOfLists(zigzagLevelOrder(root));

        // 3. 每層最後一個節點
        System.out.println("每層最後一個節點:");
        System.out.println(rightmostEachLevel(root));

        // 4. 垂直層序走訪
        System.out.println("垂直層序走訪:");
        printListOfLists(verticalOrderTraversal(root));
    }

    // 輔助函式：列印 List<List<Integer>>
    private static void printListOfLists(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
