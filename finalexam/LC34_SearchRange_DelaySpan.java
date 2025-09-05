package finalexam;

public class LC34_SearchRange_DelaySpan {
    
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = {-1, -1}; // 預設結果為 [-1, -1]
            int left = 0, right = nums.length - 1;

            // 找左邊界
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            // 檢查左邊界是否有效
            if (left >= nums.length || nums[left] != target) {
                return result; // 未找到目標值，回傳 [-1, -1]
            }
            result[0] = left; // 記錄左邊界

            // 找右邊界
            right = nums.length - 1; // 重置右邊界
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            result[1] = right; // 記錄右邊界

            return result; // 回傳結果
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC34_SearchRange_DelaySpan().new Solution();

        // 測試案例
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        int[] range1 = sol.searchRange(nums1, target1);
        System.out.print("Input: nums = [5,7,7,8,8,10], target = 8 → Output: [");
        System.out.print(range1[0] + ", " + range1[1]);
        System.out.println("]"); // [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        int[] range2 = sol.searchRange(nums2, target2);
        System.out.print("Input: nums = [5,7,7,8,8,10], target = 6 → Output: [");
        System.out.print(range2[0] + ", " + range2[1]);
        System.out.println("]"); // [-1,-1]
        int[] nums3 = {};
        int target3 = 0;
        int[] range3 = sol.searchRange(nums3, target3);
        System.out.print("Input: nums = [], target = 0 → Output: [");
        System.out.print(range3[0] + ", " + range3[1]);
        System.out.println("]"); // [-1,-1]
        System.out.println("done");
    }
}
