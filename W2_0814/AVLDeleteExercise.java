package W2_0814;

public class AVLDeleteExercise {

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
            this.height = Math.max(lh, rh) + 1;
        }

        int getBalance() {
            int lh = (left != null) ? left.height : 0;
            int rh = (right != null) ? right.height : 0;
            return lh - rh;
        }
    }

    // 左旋
    static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();
        return y;
    }

    // 右旋
    static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();
        return x;
    }

    // 插入節點
    static AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);
        if (key < node.data) node.left = insert(node.left, key);
        else if (key > node.data) node.right = insert(node.right, key);
        else return node; // 重複不插入

        node.updateHeight();
        int balance = node.getBalance();

        // 平衡操作
        if (balance > 1 && key < node.left.data) return rightRotate(node); // LL
        if (balance < -1 && key > node.right.data) return leftRotate(node); // RR
        if (balance > 1 && key > node.left.data) { // LR
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.data) { // RL
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 找最小節點
    static AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // 刪除節點
    static AVLNode deleteNode(AVLNode root, int key) {
        if (root == null) return null;

        // BST 刪除
        if (key < root.data) root.left = deleteNode(root.left, key);
        else if (key > root.data) root.right = deleteNode(root.right, key);
        else {
            // 找到節點
            if (root.left == null || root.right == null) { // 0或1個子節點
                AVLNode temp = (root.left != null) ? root.left : root.right;
                if (temp == null) { // 葉子節點
                    root = null;
                } else { // 只有一個子節點
                    root = temp;
                }
            } else { // 兩個子節點
                AVLNode temp = minValueNode(root.right); // 找後繼
                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }

        if (root == null) return null;

        // 更新高度
        root.updateHeight();

        // 平衡檢查
        int balance = root.getBalance();

        // LL
        if (balance > 1 && root.left.getBalance() >= 0) return rightRotate(root);
        // LR
        if (balance > 1 && root.left.getBalance() < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // RR
        if (balance < -1 && root.right.getBalance() <= 0) return leftRotate(root);
        // RL
        if (balance < -1 && root.right.getBalance() > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // 中序遍歷
    static void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;

        // 建立樹
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) {
            root = insert(root, v);
        }

        System.out.print("初始中序遍歷: ");
        inorder(root);
        System.out.println();

        // 刪除葉子節點
        root = deleteNode(root, 20);
        System.out.print("刪除葉子節點 20: ");
        inorder(root);
        System.out.println();

        // 刪除只有一個子節點的節點
        root = deleteNode(root, 30);
        System.out.print("刪除只有一個子節點 30: ");
        inorder(root);
        System.out.println();

        // 刪除有兩個子節點的節點
        root = deleteNode(root, 50);
        System.out.print("刪除有兩個子節點 50: ");
        inorder(root);
        System.out.println();
    }
}