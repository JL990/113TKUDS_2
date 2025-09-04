package LeetCode;

public class lt_34_FindFirstandLastPositionofElementinSortedArray {
    /**
     * 在排序陣列中找出目標值的第一個與最後一個位置
     * 時間複雜度：O(log n)，n 為陣列長度
     * 兩次二分搜尋分別找最左與最右索引
     */
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = {-1, -1};
            int left = binarySearch(nums, target, true);  // 找最左邊
            int right = binarySearch(nums, target, false); // 找最右邊
            result[0] = left;
            result[1] = right;
            return result;        
        }

        /**
         * 二分搜尋，根據 isSearchingLeft 決定找最左或最右
         * @param nums 排序陣列
         * @param target 目標值
         * @param isSearchingLeft 是否找最左邊
         * @return 目標值的索引，若不存在則回傳 -1
         */
        private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
            int left = 0;
            int right = nums.length - 1;
            int idx = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                
                if (nums[mid] > target) {
                    right = mid - 1; // 目標在左半部
                } else if (nums[mid] < target) {
                    left = mid + 1; // 目標在右半部
                } else {
                    idx = mid; // 找到目標
                    if (isSearchingLeft) {
                        right = mid - 1; // 繼續往左找
                    } else {
                        left = mid + 1; // 繼續往右找
                    }
                }
            }

            return idx;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_34_FindFirstandLastPositionofElementinSortedArray().new Solution();

        // 測試案例
        int[] nums1 = {5,7,7,8,8,10};
        int[] res1 = sol.searchRange(nums1, 8);
        System.out.println("Input: nums = [5,7,7,8,8,10], target = 8 → Output: [" + res1[0] + ", " + res1[1] + "]"); // [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        int[] res2 = sol.searchRange(nums2, 6);
        System.out.println("Input: nums = [5,7,7,8,8,10], target = 6 → Output: [" + res2[0] + ", " + res2[1] + "]"); // [-1,-1]

        int[] nums3 = {};
        int[] res3 = sol.searchRange(nums3, 0);
        System.out.println("Input: nums = [], target = 0 → Output: [" + res3[0] + ", " + res3[1] + "]"); // [-1,-1]

        System.out.println("done");
    }
}
