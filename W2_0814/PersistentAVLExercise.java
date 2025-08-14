package W2_0814;

import java.util.ArrayList;
import java.util.List;

public class PersistentAVLExercise {

    // 不可變節點
    static class AVLNode {
        final int data;
        final int height;
        final AVLNode left, right;

        AVLNode(int data, AVLNode left, AVLNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            this.height = Math.max(lh, rh) + 1;
        }

        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    static class PersistentAVL {
        List<AVLNode> versions = new ArrayList<>(); // 儲存每個版本的根

        PersistentAVL() {
            versions.add(null); // 初始版本 0
        }

        AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            // 路徑複製
            AVLNode newY = new AVLNode(y.data, T2, y.right);
            AVLNode newX = new AVLNode(x.data, x.left, newY);
            return newX;
        }

        AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            // 路徑複製
            AVLNode newX = new AVLNode(x.data, x.left, T2);
            AVLNode newY = new AVLNode(y.data, newX, y.right);
            return newY;
        }

        AVLNode insertNode(AVLNode node, int key) {
            if (node == null) return new AVLNode(key, null, null);

            AVLNode newNode;
            if (key < node.data) {
                newNode = new AVLNode(node.data, insertNode(node.left, key), node.right);
            } else if (key > node.data) {
                newNode = new AVLNode(node.data, node.left, insertNode(node.right, key));
            } else {
                return node; // 不允許重複
            }

            int balance = newNode.getBalance();

            // LL
            if (balance > 1 && key < newNode.left.data) return rightRotate(newNode);
            // RR
            if (balance < -1 && key > newNode.right.data) return leftRotate(newNode);
            // LR
            if (balance > 1 && key > newNode.left.data) {
                AVLNode rotatedLeft = leftRotate(newNode.left);
                AVLNode newRotated = new AVLNode(newNode.data, rotatedLeft, newNode.right);
                return rightRotate(newRotated);
            }
            // RL
            if (balance < -1 && key < newNode.right.data) {
                AVLNode rotatedRight = rightRotate(newNode.right);
                AVLNode newRotated = new AVLNode(newNode.data, newNode.left, rotatedRight);
                return leftRotate(newRotated);
            }

            return newNode;
        }

        // 插入並生成新版本
        void insert(int key) {
            AVLNode newRoot = insertNode(versions.get(versions.size() - 1), key);
            versions.add(newRoot);
        }

        // 查詢指定版本
        AVLNode getVersion(int version) {
            if (version < 0 || version >= versions.size()) return null;
            return versions.get(version);
        }

        // 中序遍歷
        void inorder(AVLNode node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.data + " ");
                inorder(node.right);
            }
        }

        void printVersion(int version) {
            System.out.print("版本 " + version + ": ");
            inorder(getVersion(version));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PersistentAVL tree = new PersistentAVL();

        // 版本 1
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(25);
        tree.insert(5);

        // 印出歷史版本
        for (int v = 0; v < tree.versions.size(); v++) {
            tree.printVersion(v);
        }

        // 範例: 查詢版本 3
        System.out.print("版本 3 的中序遍歷: ");
        tree.inorder(tree.getVersion(3));
        System.out.println();
    }
}
