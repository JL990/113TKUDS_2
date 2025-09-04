package LeetCode;

import java.util.Arrays;

public class lt_16_3SumClosest {
    /**
     * 求陣列中三數之和最接近 target 的組合
     * 時間複雜度：O(n^2)，n 為陣列長度
     * 先排序陣列（O(nlogn)），再用雙指針遍歷所有可能組合（O(n^2)）
     */
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums); // 將陣列排序，方便雙指針遍歷
            int closestSum = nums[0] + nums[1] + nums[2]; // 初始化最接近的三數和

            // 固定第一個數，雙指針尋找剩下兩個數
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1, right = nums.length - 1;

                while (left < right) {
                    int currentSum = nums[i] + nums[left] + nums[right]; // 計算三數之和

                    // 若目前三數和更接近 target，則更新結果
                    if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                        closestSum = currentSum;
                    }

                    // 根據三數和與 target 的大小調整指針
                    if (currentSum < target) {
                        left++; // 和太小，左指針右移
                    } else {
                        right--; // 和太大，右指針左移
                    }
                }
            }

            return closestSum;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_16_3SumClosest().new Solution();

        // 測試案例
        System.out.println("Input: nums = [-1,2,1,-4], target = 1 → Output: " + sol.threeSumClosest(new int[]{-1,2,1,-4}, 1)); // 2
        System.out.println("Input: nums = [0,0,0], target = 1 → Output: " + sol.threeSumClosest(new int[]{0,0,0}, 1)); // 0

        System.out.println("done");
    }
}
