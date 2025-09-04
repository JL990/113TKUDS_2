package LeetCode;

import java.util.Arrays;

public class lt_31_NextPermutation {
    /**
     * 求下一個排列（字典序）
     * 時間複雜度：O(n)，n 為陣列長度
     * 只需遍歷一次陣列即可完成交換與反轉
     */
    class Solution {
        public void nextPermutation(int[] nums) {
            int n = nums.length;
            int pivotIndex = -1;
            // 從右往左找到第一個遞減的位置
            for(int i = n-1; i > 0; i--){
                if(nums[i] > nums[i-1]){
                    pivotIndex = i-1;
                    break;
                }
            }
            // 若整個陣列為遞減，直接排序成最小排列
            if(pivotIndex == -1){
                Arrays.sort(nums);
                return;
            }
            int nextGreaterElementIndex = -1;
            // 從右往左找到比 pivot 大的最小元素
            for(int i = n-1; i > 0; i--){
                if(nums[i] > nums[pivotIndex]){
                    nextGreaterElementIndex = i;
                    break;
                }
            }
            // 交換 pivot 與 nextGreaterElement
            int temp = nums[nextGreaterElementIndex];
            nums[nextGreaterElementIndex] = nums[pivotIndex];
            nums[pivotIndex] = temp;

            // 將 pivot 右側的元素反轉
            int i = pivotIndex + 1, j = n - 1;
            while(i < j){
                int x = nums[i];
                nums[i] = nums[j];
                nums[j] = x;
                i++;
                j--;
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_31_NextPermutation().new Solution();

        // 測試案例
        int[] nums1 = {1,2,3};
        sol.nextPermutation(nums1);
        System.out.print("Input: nums = [1,2,3] → Output: [");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]);
            if (i < nums1.length - 1) System.out.print(", ");
        }
        System.out.println("]"); // [1,3,2]

        int[] nums2 = {3,2,1};
        sol.nextPermutation(nums2);
        System.out.print("Input: nums = [3,2,1] → Output: [");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i]);
            if (i < nums2.length - 1) System.out.print(", ");
        }
        System.out.println("]"); // [1,2,3]

        int[] nums3 = {1,1,5};
        sol.nextPermutation(nums3);
        System.out.print("Input: nums = [1,1,5] → Output: [");
        for (int i = 0; i < nums3.length; i++) {
            System.out.print(nums3[i]);
            if (i < nums3.length - 1) System.out.print(", ");
        }
        System.out.println("]"); // [1,5,1]

        int[] nums4 = {1,3,2};
        sol.nextPermutation(nums4);
        System.out.print("Input: nums = [1,3,2] → Output: [");
        for (int i = 0; i < nums4.length; i++) {
            System.out.print(nums4[i]);
            if (i < nums4.length - 1) System.out.print(", ");
        }
        System.out.println("]"); // [2,1,3]

        System.out.println("done");
    }
}
