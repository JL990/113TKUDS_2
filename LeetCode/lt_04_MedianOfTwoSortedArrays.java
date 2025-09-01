package LeetCode;

public class lt_04_MedianOfTwoSortedArrays {
    static class Solution {
        private int p1 = 0, p2 = 0;

        // 取兩個陣列當前指標的最小值，並移動指標
        private int getMin(int[] nums1, int[] nums2) {
            if (p1 < nums1.length && p2 < nums2.length) {
                return nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
            } else if (p1 < nums1.length) {
                return nums1[p1++];
            } else if (p2 < nums2.length) {
                return nums2[p2++];
            }
            return -1;
        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if ((m + n) % 2 == 0) {
                for (int i = 0; i < (m + n) / 2 - 1; ++i) {
                    getMin(nums1, nums2);
                }
                return (double) (getMin(nums1, nums2) + getMin(nums1, nums2)) / 2;
            } else {
                for (int i = 0; i < (m + n) / 2; ++i) {
                    getMin(nums1, nums2);
                }
                return getMin(nums1, nums2);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] test1 = {1, 3};
        int[] test2 = {2};
        int[] test3 = {1, 2};
        int[] test4 = {3, 4};

        System.out.println("Input: [1,3], [2] → Output: " + sol.findMedianSortedArrays(test1, test2)); // 2.0
        System.out.println("Input: [1,2], [3,4] → Output: " + sol.findMedianSortedArrays(test3, test4)); // 2.5
    }
}