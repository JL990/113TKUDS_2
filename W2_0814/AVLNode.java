package W2_0814;

public class AVLNode {
    int data;
    AVLNode left, right;
    int height;
    
    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }
    
    // 計算平衡因子
    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }
    
    // 更新節點高度
    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    //---
    // 插入新節點（回傳新的子樹根）
    public AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node; // 不插入重複值
        }

        node.updateHeight();
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 左旋
    private AVLNode leftRotate(AVLNode y) {
        AVLNode x = y.right;
        AVLNode T2 = x.left;

        x.left = y;
        y.right = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 右旋
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }
    public void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    public static void main(String[] args) {
        AVLNode root = null;
        AVLNode tree = new AVLNode(0); // 只為了呼叫方法用，不儲存此值

        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) {
            root = tree.insert(root, v);
        }

        System.out.println("前序遍歷結果：");
        tree.preorder(root);
    }
}

