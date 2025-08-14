package W2_0814;

import java.util.ArrayList;
import java.util.List;

public class AVLLeaderboardSystem {

    static class Node {
        String player;
        int score;
        int height;
        int size; // 子樹節點數
        Node left, right;

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }

        void update() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            this.height = Math.max(lh, rh) + 1;

            int ls = (left != null) ? left.size : 0;
            int rs = (right != null) ? right.size : 0;
            this.size = ls + rs + 1;
        }

        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    static class AVLTree {
        Node root;

        // 左旋
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.update();
            y.update();
            return y;
        }

        // 右旋
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.update();
            x.update();
            return x;
        }

        // 插入節點（按照 score 排序，大分數在左）
        Node insert(Node node, String player, int score) {
            if (node == null) return new Node(player, score);

            if (score > node.score) { // 高分在左
                node.left = insert(node.left, player, score);
            } else if (score < node.score) {
                node.right = insert(node.right, player, score);
            } else { // 分數相同，依玩家名排序避免重複
                if (player.compareTo(node.player) < 0) node.left = insert(node.left, player, score);
                else node.right = insert(node.right, player, score);
            }

            node.update();
            int balance = node.getBalance();

            // 平衡操作
            if (balance > 1 && score > node.left.score) return rightRotate(node); // LL
            if (balance < -1 && score < node.right.score) return leftRotate(node);  // RR
            if (balance > 1 && score < node.left.score) { // LR
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && score > node.right.score) { // RL
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void addScore(String player, int score) {
            root = insert(root, player, score);
        }

        // 查詢玩家排名（越高分排名越前，排名從1開始）
        int getRank(Node node, String player, int score) {
            if (node == null) return 0;

            if (score > node.score) {
                return getRank(node.left, player, score);
            } else if (score < node.score) {
                int leftSize = (node.left != null) ? node.left.size : 0;
                return leftSize + 1 + getRank(node.right, player, score);
            } else { // score 相同
                int cmp = player.compareTo(node.player);
                if (cmp == 0) {
                    int leftSize = (node.left != null) ? node.left.size : 0;
                    return leftSize + 1;
                } else if (cmp < 0) {
                    return getRank(node.left, player, score);
                } else {
                    int leftSize = (node.left != null) ? node.left.size : 0;
                    return leftSize + 1 + getRank(node.right, player, score);
                }
            }
        }

        int getRank(String player, int score) {
            return getRank(root, player, score);
        }

        // 前 K 名玩家
        void topK(Node node, List<String> result, int k) {
            if (node == null || result.size() >= k) return;
            topK(node.left, result, k);  // 高分在左
            if (result.size() < k) result.add(node.player + "(" + node.score + ")");
            topK(node.right, result, k);
        }

        List<String> topK(int k) {
            List<String> result = new ArrayList<>();
            topK(root, result, k);
            return result;
        }

        // 中序遍歷（debug）
        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.player + "(" + node.score + ") ");
                inorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree leaderboard = new AVLTree();

        // 添加玩家分數
        leaderboard.addScore("Alice", 50);
        leaderboard.addScore("Bob", 70);
        leaderboard.addScore("Charlie", 60);
        leaderboard.addScore("David", 80);
        leaderboard.addScore("Eve", 65);

        // 查詢前 3 名
        System.out.println("前 3 名玩家: " + leaderboard.topK(3));

        // 查詢某玩家排名
        System.out.println("Charlie 的排名: " + leaderboard.getRank("Charlie", 60));

        // 更新玩家分數
        System.out.println("\n更新 Alice 分數至 75");
        leaderboard.addScore("Alice", 75); // 視作新增一次高分
        System.out.println("前 3 名玩家: " + leaderboard.topK(3));
        System.out.println("Alice 的排名: " + leaderboard.getRank("Alice", 75));
    }
}
