package D2_0806.ch3_Prac;

import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ==============================
    // 一般多路樹節點定義
    static class MultiWayTreeNode {
        String data;
        List<MultiWayTreeNode> children;

        MultiWayTreeNode(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    // ==============================
    // 深度優先走訪（DFS）
    public static void dfs(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        for (MultiWayTreeNode child : node.children) {
            dfs(child);
        }
    }

    // ==============================
    // 廣度優先走訪（BFS）
    public static void bfs(MultiWayTreeNode root) {
        if (root == null) return;
        Queue<MultiWayTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayTreeNode current = queue.poll();
            System.out.print(current.data + " ");
            for (MultiWayTreeNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // ==============================
    // 計算樹的高度
    public static int getHeight(MultiWayTreeNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayTreeNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return maxChildHeight + 1;
    }

    // ==============================
    // 印出每個節點的度數
    public static void printDegrees(MultiWayTreeNode node) {
        if (node == null) return;
        System.out.println("節點: " + node.data + ", 度數: " + node.children.size());
        for (MultiWayTreeNode child : node.children) {
            printDegrees(child);
        }
    }

    // ==============================
    // 模擬簡單決策樹（猜數字遊戲）
    static class DecisionNode {
        String question;
        DecisionNode yesBranch, noBranch;

        DecisionNode(String question) {
            this.question = question;
        }
    }

    public static void runDecisionTree(DecisionNode node, Scanner sc) {
        if (node.yesBranch == null && node.noBranch == null) {
            System.out.println("結果：" + node.question);
            return;
        }
        System.out.println(node.question + " (y/n)");
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            runDecisionTree(node.yesBranch, sc);
        } else {
            runDecisionTree(node.noBranch, sc);
        }
    }

    // ==============================
    // 測試主程式
    public static void main(String[] args) {
        // 建立多路樹範例
        MultiWayTreeNode root = new MultiWayTreeNode("A");
        MultiWayTreeNode b = new MultiWayTreeNode("B");
        MultiWayTreeNode c = new MultiWayTreeNode("C");
        MultiWayTreeNode d = new MultiWayTreeNode("D");
        MultiWayTreeNode e = new MultiWayTreeNode("E");
        MultiWayTreeNode f = new MultiWayTreeNode("F");

        root.children.add(b);
        root.children.add(c);
        b.children.add(d);
        b.children.add(e);
        c.children.add(f);

        System.out.println("DFS 走訪:");
        dfs(root);
        System.out.println("\nBFS 走訪:");
        bfs(root);

        System.out.println("\n樹的高度: " + getHeight(root));
        System.out.println("每個節點的度數:");
        printDegrees(root);

        // 建立決策樹（簡易猜數字遊戲）
        DecisionNode q1 = new DecisionNode("你想的數字大於 50 嗎？");
        q1.yesBranch = new DecisionNode("你想的數字是 75？");
        q1.noBranch = new DecisionNode("你想的數字是 25？");

        System.out.println("\n猜數字遊戲開始！");
        Scanner sc = new Scanner(System.in);
        runDecisionTree(q1, sc);
    }
}
