package LeetCode;

public class lt_33_SearchinRotatedSortedArray {
    /**
     * 在旋轉排序陣列中搜尋目標值
     * 時間複雜度：O(log n)，n 為陣列長度
     * 使用二分搜尋法，每次可排除一半區間
     */
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            // 二分搜尋
            while (left <= right) {
                int mid = (left + right) / 2;

                if (nums[mid] == target) {
                    return mid; // 找到目標值，回傳索引
                } else if (nums[mid] >= nums[left]) {
                    // 左半部有序
                    if (nums[left] <= target && target <= nums[mid]) {
                        right = mid - 1; // 目標在左半部
                    } else {
                        left = mid + 1; // 目標在右半部
                    }
                } else {
                    // 右半部有序
                    if (nums[mid] <= target && target <= nums[right]) {
                        left = mid + 1; // 目標在右半部
                    } else {
                        right = mid - 1; // 目標在左半部
                    }
                }
            }

            return -1; // 未找到目標值        
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_33_SearchinRotatedSortedArray().new Solution();

        // 測試案例
        int[] nums1 = {4,5,6,7,0,1,2};
        int res1 = sol.search(nums1, 0);
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = 0 → Output: " + res1); // 4

        int[] nums2 = {4,5,6,7,0,1,2};
        int res2 = sol.search(nums2, 3);
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = 3 → Output: " + res2); // -1

        int[] nums3 = {1};
        int res3 = sol.search(nums3, 0);
        System.out.println("Input: nums = [1], target = 0 → Output: " + res3); // -1

        System.out.println("done");
    }
}
