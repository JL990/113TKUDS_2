package LeetCode;

public class lt_21_MergeTwoSortedLists {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
 
    /**
     * 合併兩個已排序的連結串列
     * 時間複雜度：O(m+n)，m、n 分別為兩個串列的長度
     * 只需遍歷一次兩個串列即可完成合併
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode res = new ListNode(); // 建立虛擬頭節點
            ListNode temp = res; // 用於串接新節點
            while (list1 != null && list2 != null) {
                // 比較兩串列目前節點，取較小者加入新串列
                if (list1.val < list2.val) {
                    temp.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    temp.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                temp = temp.next;
            }
            // 若其中一串列尚未遍歷完，直接串接剩餘部分
            if (list1 != null) {
                temp.next = list1;
            }
            if (list2 != null) {
                temp.next = list2;
            }
            return res.next; // 回傳合併後的串列（去除虛擬頭節點）
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_21_MergeTwoSortedLists().new Solution();

        // 測試案例
        ListNode l1 = new lt_21_MergeTwoSortedLists().new ListNode(1, new lt_21_MergeTwoSortedLists().new ListNode(2, new lt_21_MergeTwoSortedLists().new ListNode(4)));
        ListNode l2 = new lt_21_MergeTwoSortedLists().new ListNode(1, new lt_21_MergeTwoSortedLists().new ListNode(3, new lt_21_MergeTwoSortedLists().new ListNode(4)));
        ListNode merged = sol.mergeTwoLists(l1, l2);
        System.out.print("Input: list1 = [1,2,4], list2 = [1,3,4] → Output: [");
        while (merged != null) {
            System.out.print(merged.val);
            merged = merged.next;
            if (merged != null) System.out.print(",");
        }
        System.out.println("]"); // [1,1,2,3,4,4]

        System.out.println("done");
    }
}
