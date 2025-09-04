package LeetCode;

public class lt_25_ReverseNodesinkGroup {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 每 k 個一組反轉連結串列
     * 時間複雜度：O(n)，n 為連結串列長度
     * 每個節點只會被處理一次
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k)
        {
            ListNode dummy = new ListNode(0); // 虛擬頭節點
            dummy.next = head;
            ListNode groupprev = dummy; // 指向每組的前一個節點

            while(true)
            {
                ListNode kth_node = helper(groupprev, k); // 找到第 k 個節點
                if(kth_node == null) break; // 不足 k 個則結束
                ListNode groupnext = kth_node.next; // 下一組的起始節點
                ListNode prev = groupnext;
                ListNode curr = groupprev.next;

                // 反轉本組節點
                while(curr != groupnext)
                {
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                }
                // 串接反轉後的節點
                ListNode temp = groupprev.next;
                groupprev.next = prev;
                groupprev = temp;
            }
            return dummy.next;
        }

        /**
         * 找到從 node 開始第 k 個節點
         * @param node 起始節點
         * @param k 距離
         * @return 第 k 個節點，若不足 k 個則回傳 null
         */
        public ListNode helper(ListNode node, int k)
        {
            while(node != null && k > 0)
            {
                node = node.next;
                k--;
            }
            return node;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_25_ReverseNodesinkGroup().new Solution();
        ListNode head = new lt_25_ReverseNodesinkGroup().new ListNode(1, new lt_25_ReverseNodesinkGroup().new ListNode(2, new lt_25_ReverseNodesinkGroup().new ListNode(3, new lt_25_ReverseNodesinkGroup().new ListNode(4, new lt_25_ReverseNodesinkGroup().new ListNode(5)))));
        int k = 2;
        ListNode reversed = sol.reverseKGroup(head, k);
        System.out.print("Input: head = [1,2,3,4,5], k = 2 → Output: [");
        while (reversed != null) {
            System.out.print(reversed.val);
            reversed = reversed.next;
            if (reversed != null) System.out.print(",");
        }
        System.out.println("]"); // [2,1,4,3,5]
        System.out.println("done");
    }
}
