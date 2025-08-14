package W2_0814;

import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    // 節點類別
    static class AVLNode {
        int data, height;
        AVLNode left, right;

        AVLNode(int data) {
            this.data = data;
            this.height = 1;
        }

        void updateHeight() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            height = Math.max(lh, rh) + 1;
        }

        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    // AVL 樹
    static class AVLTree {
        AVLNode root;

        // 左旋
        AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.updateHeight();
            y.updateHeight();
            return y;
        }

        // 右旋
        AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.updateHeight();
            x.updateHeight();
            return x;
        }

        // 插入節點
        AVLNode insert(AVLNode node, int key) {
            if (node == null) return new AVLNode(key);

            if (key < node.data) node.left = insert(node.left, key);
            else if (key > node.data) node.right = insert(node.right, key);
            else return node;

            node.updateHeight();
            int balance = node.getBalance();

            // 平衡操作
            if (balance > 1 && key < node.left.data) return rightRotate(node);
            if (balance < -1 && key > node.right.data) return leftRotate(node);
            if (balance > 1 && key > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && key < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void insert(int key) {
            root = insert(root, key);
        }

        // 範圍查詢
        List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        void rangeQueryHelper(AVLNode node, int min, int max, List<Integer> result) {
            if (node == null) return;

            // 利用 BST 剪枝
            if (node.data > min) {
                rangeQueryHelper(node.left, min, max, result);
            }

            if (node.data >= min && node.data <= max) {
                result.add(node.data);
            }

            if (node.data < max) {
                rangeQueryHelper(node.right, min, max, result);
            }
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) tree.insert(v);

        int min = 35, max = 65;
        List<Integer> rangeResult = tree.rangeQuery(min, max);

        System.out.println("AVL 樹中介於 " + min + " 到 " + max + " 的元素:");
        System.out.println(rangeResult);
    }
}
