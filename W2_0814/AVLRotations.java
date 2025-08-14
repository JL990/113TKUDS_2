package W2_0814;

public class AVLRotations {
    
    // 右旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 執行旋轉
        x.right = y;
        y.left = T2;
        
        // 更新高度
        y.updateHeight();
        x.updateHeight();
        
        return x; // 新的根節點
    }
    
    // 左旋操作
    // 時間複雜度: O(1), 空間複雜度: O(1)
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 執行旋轉
        y.left = x;
        x.right = T2;
        
        // 更新高度
        x.updateHeight();
        y.updateHeight();
        
        return y; // 新的根節點
    }

    public static void main(String[] args) {
        AVLNode root = null;
        AVLNode tree = new AVLNode(0);

        int[] values = {70, 20, 30, 40, 50, 25};
        for (int v : values) {
            root = tree.insert(root, v);
        }

        System.out.println("前序遍歷結果：");
        tree.preorder(root);
    }
}

