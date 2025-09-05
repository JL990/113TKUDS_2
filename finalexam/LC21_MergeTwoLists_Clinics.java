package finalexam;

public class LC21_MergeTwoLists_Clinics {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 建立虛擬頭節點，方便操作
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            // 同時遍歷兩個鏈結串列，將較小的節點接到結果串列
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    current.next = list1;
                    list1 = list1.next;
                } else {
                    current.next = list2;
                    list2 = list2.next;
                }
                current = current.next;
            }

            // 將剩餘的節點接到結果串列
            if (list1 != null) {
                current.next = list1;
            } else if (list2 != null) {
                current.next = list2;
            }

            return dummy.next; // 返回合併後的串列，跳過虛擬頭節點
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC21_MergeTwoLists_Clinics().new Solution();

        // 測試案例
        // Input: list1 = [1,2,4], list2 = [1,3,4] → Output: [1,1,2,3,4,4]
        ListNode list1 = new LC21_MergeTwoLists_Clinics().new ListNode(1);
        list1.next = new LC21_MergeTwoLists_Clinics().new ListNode(2);
        list1.next.next = new LC21_MergeTwoLists_Clinics().new ListNode(4);
        ListNode list2 = new LC21_MergeTwoLists_Clinics().new ListNode(1);
        list2.next = new LC21_MergeTwoLists_Clinics().new ListNode(3);
        list2.next.next = new LC21_MergeTwoLists_Clinics().new ListNode(4);
        ListNode result = sol.mergeTwoLists(list1, list2);

        System.out.print("Input: list1 = [1,2,4], list2 = [1,3,4] → Output: [");
        while(result != null){
            System.out.print(result.val);
            result = result.next;
            if(result != null) System.out.print(",");
        }
        System.out.println("]");

        // Input: list1 = [], list2 = [] → Output: []
        ListNode list3 = null;
        ListNode list4 = null;
        ListNode result2 = sol.mergeTwoLists(list3, list4);
        System.out.print("Input: list1 = [], list2 = [] → Output: [");
        while(result2 != null){
            System.out.print(result2.val);
            result2 = result2.next;
            if(result2 != null) System.out.print(",");
        }
        System.out.println("]");

        // Input: list1 = [], list2 = [0] → Output: [0]
        ListNode list5 = null;
        ListNode list6 = new LC21_MergeTwoLists_Clinics().new ListNode(0);
        ListNode result3 = sol.mergeTwoLists(list5, list6);
        System.out.print("Input: list1 = [], list2 = [0] → Output: [");
        while(result3 != null){
            System.out.print(result3.val);
            result3 = result3.next;
            if(result3 != null) System.out.print(",");
        }
        System.out.println("]");

        System.out.println("done");
    }
}       