package LeetCode;

public class lt_24_SwapNodesinPairs {
    /**
     * 兩兩交換連結串列中的節點
     * 時間複雜度：O(n)，n 為連結串列長度
     * 只需遍歷一次連結串列即可完成交換
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(0, head); // 虛擬頭節點，方便處理頭節點交換
            ListNode prev = dummy, cur = head;

            // 每次交換 cur 與 cur.next
            while (cur != null && cur.next != null) {
                ListNode npn = cur.next.next; // 下一組要處理的節點
                ListNode second = cur.next;   // 第二個節點

                // 交換節點
                second.next = cur;
                cur.next = npn;
                prev.next = second;

                // 移動指標到下一組
                prev = cur;
                cur = npn;
            }

            return dummy.next; // 回傳交換後的串列頭
        }

        
    }
    public class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }


    public static void main(String[] args) {
        Solution sol = new lt_24_SwapNodesinPairs().new Solution();
        ListNode head = new lt_24_SwapNodesinPairs().new ListNode(1, new lt_24_SwapNodesinPairs().new ListNode(2, new lt_24_SwapNodesinPairs().new ListNode(3, new lt_24_SwapNodesinPairs().new ListNode(4))));
        ListNode swapped = sol.swapPairs(head);
        System.out.print("Input: head = [1,2,3,4] → Output: [");
        while (swapped != null) {
            System.out.print(swapped.val);
            swapped = swapped.next;
            if (swapped != null) System.out.print(",");
        }
        System.out.println("]"); // [2,1,4,3]
        System.out.println("done");
    }

}
