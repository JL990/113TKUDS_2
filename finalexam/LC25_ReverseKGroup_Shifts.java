package finalexam;

public class LC25_ReverseKGroup_Shifts {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            // 建立虛擬頭節點，方便操作
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode groupPrev = dummy;

            while (true) {
                // 找到第 k 個節點
                ListNode kth = getKthNode(groupPrev, k);
                if (kth == null) break; // 不足 k 個節點，結束

                ListNode groupNext = kth.next; // 下一組的起始節點
                // 反轉當前組
                ListNode prev = groupNext;
                ListNode current = groupPrev.next;

                while (current != groupNext) {
                    ListNode temp = current.next;
                    current.next = prev;
                    prev = current;
                    current = temp;
                }

                // 連接反轉後的組
                ListNode temp = groupPrev.next; // 反轉前的第一個節點
                groupPrev.next = kth; // 連接到反轉後的第一個節點
                groupPrev = temp; // 移動到下一組的前一個節點
            }

            return dummy.next; // 返回新的頭節點，跳過虛擬頭節點
        }

        // 輔助函數：找到從 node 開始的第 k 個節點
        private ListNode getKthNode(ListNode node, int k) {
            while (node != null && k > 0) {
                node = node.next;
                k--;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC25_ReverseKGroup_Shifts().new Solution();

        // 測試案例
        // Input: head = [1,2,3,4,5], k = 2 → Output: [2,1,4,3,5]
        ListNode head1 = new LC25_ReverseKGroup_Shifts().new ListNode(1);
        head1.next = new LC25_ReverseKGroup_Shifts().new ListNode(2);
        head1.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(3);
        head1.next.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(4);
        head1.next.next.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(5);
        ListNode result1 = sol.reverseKGroup(head1, 2);
        System.out.print("Input: head = [1,2,3,4,5], k = 2 → Output: [");   
        while(result1 != null){
            System.out.print(result1.val);
            result1 = result1.next;
            if(result1 != null) System.out.print(",");
        }
        System.out.println("]");
        // Input: head = [1,2,3,4,5], k = 3 → Output: [3,2,1,4,5]
        ListNode head2 = new LC25_ReverseKGroup_Shifts().new ListNode(1);
        head2.next = new LC25_ReverseKGroup_Shifts().new ListNode(2);
        head2.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(3);
        head2.next.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(4);
        head2.next.next.next.next = new LC25_ReverseKGroup_Shifts().new ListNode(5);
        ListNode result2 = sol.reverseKGroup(head2, 3);
        System.out.print("Input: head = [1,2,3,4,5], k = 3 → Output: [");
        while(result2 != null){
            System.out.print(result2.val);
            result2 = result2.next;
            if(result2 != null) System.out.print(",");
        }
        System.out.println("]");
        
    }
}
