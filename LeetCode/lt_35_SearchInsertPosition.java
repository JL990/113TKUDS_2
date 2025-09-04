package LeetCode;

public class lt_35_SearchInsertPosition {
    /**
     * 在排序陣列中搜尋目標值插入位置
     * 時間複雜度：O(log n)，n 為陣列長度
     * 使用二分搜尋法，每次可排除一半區間
     */
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            // 二分搜尋
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] == target) {
                    return mid; // 找到目標值，回傳索引
                } else if (nums[mid] > target) {
                    right = mid - 1; // 目標在左半部
                } else {
                    left = mid + 1; // 目標在右半部
                }
            }

            return left; // 若未找到，回傳插入位置        
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_35_SearchInsertPosition().new Solution();

        // 測試案例
        int[] nums1 = {1,3,5,6};
        int res1 = sol.searchInsert(nums1, 5);
        System.out.println("Input: nums = [1,3,5,6], target = 5 → Output: " + res1); // 2

        int[] nums2 = {1,3,5,6};
        int res2 = sol.searchInsert(nums2, 2);
        System.out.println("Input: nums = [1,3,5,6], target = 2 → Output: " + res2); // 1

        int[] nums3 = {1,3,5,6};
        int res3 = sol.searchInsert(nums3, 7);
        System.out.println("Input: nums = [1,3,5,6], target = 7 → Output: " + res3); // 4

        int[] nums4 = {1,3,5,6};
        int res4 = sol.searchInsert(nums4, 0);
        System.out.println("Input: nums = [1,3,5,6], target = 0 → Output: " + res4); // 0

        System.out.println("done");
    }
}
