package LeetCode;

public class lt_02_addtwonum {
    // 定義 ListNode 類別
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 解法
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {
                int sum = carry;
                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }
                carry = sum / 10;
                cur.next = new ListNode(sum % 10);
                cur = cur.next;
            }

            return dummy.next;
        }
    }

    // 測試
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試資料：l1 = [2,4,3], l2 = [5,6,4]
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = sol.addTwoNumbers(l1, l2);

        // 輸出結果 (應該是 [7,0,8])
        System.out.print("Result: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}