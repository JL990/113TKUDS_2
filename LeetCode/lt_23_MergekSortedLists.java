package LeetCode;
//import java.util.List;

public class lt_23_MergekSortedLists {
    /**
     * 合併 k 個已排序的連結串列
     * 時間複雜度：O(Nlogk)，N 為所有節點總數，k 為串列數量
     * 使用分治法，每次合併兩個串列，合併層數為 logk
     */
    class Solution {
        /**
         * 合併兩個已排序的連結串列（遞迴）
         * @param l1 第一個串列
         * @param l2 第二個串列
         * @return 合併後的串列
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }

        /**
         * 合併 k 個已排序的連結串列
         * @param lists 串列陣列
         * @return 合併後的串列
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            return divideAndConquer(lists, 0, lists.length - 1);
        }

        /**
         * 分治法合併串列
         * @param lists 串列陣列
         * @param left 左邊界
         * @param right 右邊界
         * @return 合併後的串列
         */
        private ListNode divideAndConquer(ListNode[] lists, int left, int right) {
            if (left == right) return lists[left];

            int mid = left + (right - left) / 2;
            ListNode l1 = divideAndConquer(lists, left, mid);
            ListNode l2 = divideAndConquer(lists, mid + 1, right);
            return mergeTwoLists(l1, l2);
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
        Solution sol = new lt_23_MergekSortedLists().new Solution();

        // 測試案例
        ListNode l1 = new lt_23_MergekSortedLists().new ListNode(1, new lt_23_MergekSortedLists().new ListNode(4, new lt_23_MergekSortedLists().new ListNode(5)));
        ListNode l2 = new lt_23_MergekSortedLists().new ListNode(1, new lt_23_MergekSortedLists().new ListNode(3, new lt_23_MergekSortedLists().new ListNode(4)));
        ListNode l3 = new lt_23_MergekSortedLists().new ListNode(2, new lt_23_MergekSortedLists().new ListNode(6));
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode merged = sol.mergeKLists(lists);
        System.out.print("Input: lists = [[1,4,5],[1,3,4],[2,6]] → Output: [");
        while (merged != null) {
            System.out.print(merged.val);
            merged = merged.next;
            if (merged != null) System.out.print(",");
        }
        System.out.println("]"); // [1,1,2,3,4,4,5,6]

        System.out.println("done");
    }
}
