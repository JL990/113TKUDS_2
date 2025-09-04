package LeetCode;

public class lt_26_RemoveDuplicatesfromSortedArray {
    /**
     * 移除排序陣列中的重複元素
     * 時間複雜度：O(n)，n 為陣列長度
     * 只需遍歷一次陣列即可完成去重
     */
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0; // 若陣列為空，直接回傳 0

            int i = 1; // i 指向下個不重複元素要放的位置

            // j 從第二個元素開始遍歷
            for (int j = 1; j < nums.length; j++) {
                // 若遇到新元素（與前一個不同）
                if (nums[j] != nums[i - 1]) {
                    nums[i] = nums[j]; // 將新元素放到 i 位置
                    i++; // i 往後移
                }
            }

            return i; // 回傳不重複元素的數量        
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_26_RemoveDuplicatesfromSortedArray().new Solution();

        // 測試案例
        int[] nums1 = {1,1,2};
        int len1 = sol.removeDuplicates(nums1);
        System.out.print("Input: nums = [1,1,2] → Output: " + len1 + ", nums = [");
        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i]);
            if (i < len1 - 1) System.out.print(", ");
        }
        System.out.println("]"); // 2, [1,2]

        int[] nums2 = {0,0,1,1,1,2,2,3,3,4};
        int len2 = sol.removeDuplicates(nums2);
        System.out.print("Input: nums = [0,0,1,1,1,2,2,3,3,4] → Output: " + len2 + ", nums = [");
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i]);
            if (i < len2 - 1) System.out.print(", ");
        }
        System.out.println("]"); // 5, [0,1,2,3,4]

        System.out.println("done");
    }
}
