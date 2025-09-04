package LeetCode;

public class lt_27_RemoveElement {
    /**
     * 移除陣列中所有等於 val 的元素
     * 時間複雜度：O(n)，n 為陣列長度
     * 只需遍歷一次陣列即可完成移除
     */
    class Solution {
        public int removeElement(int[] nums, int val) {
            int k = 0; // k 指向下個要保留元素的位置

            // 遍歷陣列
            for (int i = 0; i < nums.length; i++) {
                // 若當前元素不等於 val，則保留
                if (nums[i] != val) {
                    nums[k] = nums[i]; // 將元素移到前面
                    k++; // k 往後移
                }
            }

            return k; // 回傳移除後的長度        
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_27_RemoveElement().new Solution();

        // 測試案例
        int[] nums1 = {3,2,2,3};
        int len1 = sol.removeElement(nums1, 3);
        System.out.print("Input: nums = [3,2,2,3], val = 3 → Output: " + len1 + ", nums = [");
        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i]);
            if (i < len1 - 1) System.out.print(", ");
        }
        System.out.println("]"); // 2, [2,2]

        int[] nums2 = {0,1,2,2,3,0,4,2};
        int len2 = sol.removeElement(nums2, 2);
        System.out.print("Input: nums = [0,1,2,2,3,0,4,2], val = 2 → Output: " + len2 + ", nums = [");
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i]);
            if (i < len2 - 1) System.out.print(", ");
        }
        System.out.println("]"); // 5, [0,1,3,0,4]

        System.out.println("done");
    }
}
