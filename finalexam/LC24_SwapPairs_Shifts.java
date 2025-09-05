package finalexam;

public class LC24_SwapPairs_Shifts {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            // 建立虛擬頭節點，方便操作
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode current = dummy;

            // 遍歷鏈結串列，交換每對相鄰節點
            while (current.next != null && current.next.next != null) {
                ListNode first = current.next;
                ListNode second = current.next.next;

                // 交換節點
                first.next = second.next;
                second.next = first;
                current.next = second;

                // 移動到下一對節點
                current = first;
            }

            return dummy.next; // 返回新的頭節點，跳過虛擬頭節點
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC24_SwapPairs_Shifts().new Solution();

        // 測試案例
        // Input: head = [1,2,3,4] → Output: [2,1,4,3]
        ListNode head1 = new LC24_SwapPairs_Shifts().new ListNode(1);
        head1.next = new LC24_SwapPairs_Shifts().new ListNode(2);
        head1.next.next = new LC24_SwapPairs_Shifts().new ListNode(3);
        head1.next.next.next = new LC24_SwapPairs_Shifts().new ListNode(4);
        ListNode result1 = sol.swapPairs(head1);
        System.out.print("Input: head = [1,2,3,4] → Output: [");
        while(result1 != null){
            System.out.print(result1.val);
            result1 = result1.next;
            if(result1 != null) System.out.print(",");
        }
        System.out.println("]");

        // Input: head = [] → Output: []
        ListNode head2 = null;
        ListNode result2 = sol.swapPairs(head2);
        System.out.print("Input: head = [] → Output: [");
        while(result2 != null){
            System.out.print(result2.val);
            result2 = result2.next;
            if(result2 != null) System.out.print(",");
        }
        System.out.println("]");
        // Input: head = [1] → Output: [1]
        ListNode head3 = new LC24_SwapPairs_Shifts().new ListNode(1);
        ListNode result3 = sol.swapPairs(head3);
        System.out.print("Input: head = [1] → Output: [");
        while(result3 != null){
            System.out.print(result3.val);
            result3 = result3.next;
            if(result3 != null) System.out.print(","); 
        }
        System.out.println("]");
    }
}
