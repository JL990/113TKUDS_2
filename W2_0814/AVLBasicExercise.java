package W2_0814;

public class AVLBasicExercise {

    // 節點類別
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    // AVL 樹類別
    static class AVLTree {
        Node root;

        // 取得節點高度
        int height(Node n) {
            return n == null ? 0 : n.height;
        }

        // 計算平衡因子
        int getBalance(Node n) {
            return n == null ? 0 : height(n.left) - height(n.right);
        }

        // 右旋轉
        Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        // 左旋轉
        Node rotateLeft(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        // 插入節點
        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node; // 不允許重複
            }

            // 更新高度
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            // 計算平衡因子
            int balance = getBalance(node);

            // 左左
            if (balance > 1 && key < node.left.key) {
                return rotateRight(node);
            }
            // 右右
            if (balance < -1 && key > node.right.key) {
                return rotateLeft(node);
            }
            // 左右
            if (balance > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            // 右左
            if (balance < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        // 搜尋節點
        boolean search(Node node, int key) {
            if (node == null) return false;
            if (node.key == key) return true;
            return key < node.key ? search(node.left, key) : search(node.right, key);
        }

        // 計算高度（遞迴）
        int calculateHeight(Node node) {
            if (node == null) return 0;
            return Math.max(calculateHeight(node.left), calculateHeight(node.right)) + 1;
        }

        // 檢查是否為有效 AVL 樹
        boolean isValidAVL(Node node) {
            if (node == null) return true;
            int balance = getBalance(node);
            if (balance < -1 || balance > 1) return false;
            return isValidAVL(node.left) && isValidAVL(node.right);
        }

        // 中序遍歷
        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] values = {60, 20, 30, 40, 50, 25};
        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("中序遍歷: ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("搜尋 25: " + tree.search(tree.root, 25));
        System.out.println("搜尋 99: " + tree.search(tree.root, 99));
        System.out.println("樹的高度: " + tree.calculateHeight(tree.root));
        System.out.println("是否為有效 AVL 樹: " + tree.isValidAVL(tree.root));
    }
}
