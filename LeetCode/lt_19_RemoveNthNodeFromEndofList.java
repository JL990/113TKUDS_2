package LeetCode;

public class lt_19_RemoveNthNodeFromEndofList {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
 
    /**
     * 刪除連結串列中倒數第 n 個節點
     * 時間複雜度：O(L)，L 為連結串列長度
     * 需遍歷兩次連結串列：第一次計算長度，第二次找到要刪除的節點
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int count = 0; // 計算連結串列長度
            ListNode curr = head;
            ListNode dummy = new ListNode(0); // 虛擬頭節點，方便處理刪除頭節點的情況
            dummy.next = head;
            ListNode prev = dummy;

            // 第一次遍歷，計算連結串列長度
            while (curr != null) {
                count += 1;
                curr = curr.next;
            }

            curr = head;
            int nodetoRemovePos = count - n; // 要刪除的節點位置（從頭開始算）
            int secondPass = 0;

            // 第二次遍歷，找到要刪除的節點
            while (secondPass < nodetoRemovePos) {
                prev = curr;
                curr = curr.next;
                secondPass += 1;
            }

            // 刪除目標節點
            prev.next = curr.next;
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        Solution sol = new lt_19_RemoveNthNodeFromEndofList().new Solution();

        // 測試案例
        // Input: head = [1,2,3,4,5], n = 2 → Output: [1,2,3,5]
        ListNode head1 = new lt_19_RemoveNthNodeFromEndofList().new ListNode(1);
        head1.next = new lt_19_RemoveNthNodeFromEndofList().new ListNode(2);
        head1.next.next = new lt_19_RemoveNthNodeFromEndofList().new ListNode(3);
        head1.next.next.next = new lt_19_RemoveNthNodeFromEndofList().new ListNode(4);
        head1.next.next.next.next = new lt_19_RemoveNthNodeFromEndofList().new ListNode(5);
        ListNode result1 = sol.removeNthFromEnd(head1, 2);
        System.out.print("Input: head = [1,2,3,4,5], n = 2 → Output: [");
        while(result1 != null){
            System.out.print(result1.val);
            result1 = result1.next;
            if(result1 != null) System.out.print(",");
        }
        System.out.println("]");

        // Input: head = [1], n = 1 → Output: []
        ListNode head2 = new lt_19_RemoveNthNodeFromEndofList().new ListNode(1);
        ListNode result2 = sol.removeNthFromEnd(head2, 1);
        System.out.print("Input: head = [1], n = 1 → Output: [");
        while(result2 != null){
            System.out.print(result2.val);
            result2 = result2.next;
            if(result2 != null) System.out.print(",");
        }
        System.out.println("]");

        // Input: head = [1,2], n = 1 → Output: [1]
        ListNode head3 = new lt_19_RemoveNthNodeFromEndofList().new ListNode(1);
        head3.next = new lt_19_RemoveNthNodeFromEndofList().new ListNode(2);
        ListNode result3 = sol.removeNthFromEnd(head3, 1);
        System.out.print("Input: head = [1,2], n = 1 → Output: [");
        while(result3 != null){
            System.out.print(result3.val);
            result3 = result3.next;
            if(result3 != null) System.out.print(",");
        }
        System.out.println("]");
    }

}
