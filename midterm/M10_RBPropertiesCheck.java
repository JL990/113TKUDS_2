package midterm;

import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {
        int val;
        char color; // 'B' or 'R'
        Node(int v, char c) { this.val = v; this.color = c; }
    }

    static Node[] tree;

    // 性質 3: 檢查黑高度
    // 回傳黑高度；若不合法，回傳 -INF
    static int checkBlackHeight(int idx) {
        if (idx >= tree.length || tree[idx] == null) {
            return 1; // NIL 當作黑
        }
        Node cur = tree[idx];
        int left = checkBlackHeight(2*idx+1);
        if (left == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int right = checkBlackHeight(2*idx+2);
        if (right == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        if (left != right) return Integer.MIN_VALUE;

        return left + (cur.color == 'B' ? 1 : 0);
    }

    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            char c = sc.next().charAt(0);
            if (val != -1) {
                tree[i] = new Node(val, c);
            } else {
                tree[i] = null; // null node
            }
        }

        // === 性質 1: 根必須為黑 ===
        if (tree[0] != null && tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // === 性質 2: 不得紅紅相鄰 ===
        for (int i = 0; i < n; i++) {
            if (tree[i] == null) continue;
            if (tree[i].color == 'R') {
                int l = 2*i+1, r = 2*i+2;
                if (l < n && tree[l] != null && tree[l].color == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (r < n && tree[r] != null && tree[r].color == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        // === 性質 3: 黑高度一致 ===
        if (checkBlackHeight(0) == Integer.MIN_VALUE) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        // 全部通過
        System.out.println("RB Valid");
    }
}

