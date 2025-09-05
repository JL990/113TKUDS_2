package finalexam;

public class LC33_SearchRotated_RentHot {
    
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] == target) {
                    return mid; // 找到目標值，返回索引
                }

                // 判斷哪一半是有序的
                if (nums[left] <= nums[mid]) { // 左半部分有序
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1; // 目標值在左半部分
                    } else {
                        left = mid + 1; // 目標值在右半部分
                    }
                } else { // 右半部分有序
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1; // 目標值在右半部分
                    } else {
                        right = mid - 1; // 目標值在左半部分
                    }
                }
            }

            return -1; // 未找到目標值
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC33_SearchRotated_RentHot().new Solution();

        // 測試案例
        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = 0 → Output: " + sol.search(nums1, 0)); // 4

        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = 3 → Output: " + sol.search(nums2, 3)); // -1

        int[] nums3 = {1};
        System.out.println("Input: nums = [1], target = 0 → Output: " + sol.search(nums3, 0)); // -1

        System.out.println("done");
    }   
    
}
