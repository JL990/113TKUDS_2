package finalexam;
import java.util.*;

public class LC04_Median_QuakeFeeds {

    static class Solution {
        /**
         * 二分法求兩個已排序陣列的中位數
         * 時間複雜度：O(log(min(n, m)))
         */
        public double findMedianSortedArrays(double[] nums1, double[] nums2) {
            int n = nums1.length, m = nums2.length;
            // 保證 nums1 長度較小
            if (n > m) return findMedianSortedArrays(nums2, nums1);

            int left = 0, right = n;
            int totalLeft = (n + m + 1) / 2;
            while (left <= right) {
                int i = (left + right) / 2;
                int j = totalLeft - i;

                double nums1LeftMax = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
                double nums1RightMin = (i == n) ? Double.POSITIVE_INFINITY : nums1[i];
                double nums2LeftMax = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
                double nums2RightMin = (j == m) ? Double.POSITIVE_INFINITY : nums2[j];

                if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                    // 已找到正確切割
                    if ((n + m) % 2 == 1) {
                        return Math.max(nums1LeftMax, nums2LeftMax);
                    } else {
                        return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                    }
                } else if (nums1LeftMax > nums2RightMin) {
                    right = i - 1;
                } else {
                    left = i + 1;
                }
            }
            return 0.0; // 不會到這裡
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] nums1 = new double[n];
        double[] nums2 = new double[m];
        for (int i = 0; i < n; i++) nums1[i] = sc.nextDouble();
        for (int i = 0; i < m; i++) nums2[i] = sc.nextDouble();
        Solution sol = new Solution();
        double ans = sol.findMedianSortedArrays(nums1, nums2);
        System.out.printf("%.1f\n", ans);
    }
}
