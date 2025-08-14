package W2_0814;


public class AVLRotationExercise {
    public static void main(String[] args) {
        // 測試 1：右旋 (Right Rotation)
        System.out.println("=== 測試 1：右旋 (Right Rotation) ===");
        AVLNode root1 = new AVLNode(30);
        root1.left = new AVLNode(20);
        root1.left.left = new AVLNode(10);
        root1.updateHeight();
        root1.left.updateHeight();
        root1 = AVLRotations.rightRotate(root1);
        preorderPrint(root1); // 應為 20 10 30

        // 測試 2：左旋 (Left Rotation)
        System.out.println("\n=== 測試 2：左旋 (Left Rotation) ===");
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);
        root2.updateHeight();
        root2.right.updateHeight();
        root2 = AVLRotations.leftRotate(root2);
        preorderPrint(root2); // 應為 20 10 30

        // 測試 3：左右旋 (Left-Right Rotation)
        System.out.println("\n=== 測試 3：左右旋 (Left-Right Rotation) ===");
        AVLNode root3 = new AVLNode(30);
        root3.left = new AVLNode(10);
        root3.left.right = new AVLNode(20);
        root3.updateHeight();
        root3.left.updateHeight();
        root3.left = AVLRotations.leftRotate(root3.left);
        root3 = AVLRotations.rightRotate(root3);
        preorderPrint(root3); // 應為 20 10 30

        // 測試 4：右左旋 (Right-Left Rotation)
        System.out.println("\n=== 測試 4：右左旋 (Right-Left Rotation) ===");
        AVLNode root4 = new AVLNode(10);
        root4.right = new AVLNode(30);
        root4.right.left = new AVLNode(20);
        root4.updateHeight();
        root4.right.updateHeight();
        root4.right = AVLRotations.rightRotate(root4.right);
        root4 = AVLRotations.leftRotate(root4);
        preorderPrint(root4); // 應為 20 10 30
    }

    // 前序遍歷列印
    public static void preorderPrint(AVLNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderPrint(node.left);
            preorderPrint(node.right);
        }
    }
}