package finalexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class LC15_3Sum_THSRStops {
    
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            int target = 0; // 目標和為 0
            Arrays.sort(nums); // 先將陣列排序
            Set<List<Integer>> s = new HashSet<>(); // 用於去除重複三元組
            List<List<Integer>> output = new ArrayList<>(); // 儲存結果

            // 固定第一個數，雙指針尋找剩下兩個數
            for (int i = 0; i < nums.length; i++){
                int j = i + 1; // 左指針
                int k = nums.length - 1; // 右指針
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k]; // 計算三數之和
                    if (sum == target) {
                        s.add(Arrays.asList(nums[i], nums[j], nums[k])); // 加入結果集合
                        j++;
                        k--;
                    } else if (sum < target) {
                        j++; // 和太小，左指針右移
                    } else {
                        k--; // 和太大，右指針左移
                    }
                }
            }
            output.addAll(s); // 將集合轉為 List
            return output;
        }
    }
    public static void main(String[] args) {
        Solution sol = new LC15_3Sum_THSRStops().new Solution();

        // 測試案例
        System.out.println("Input: nums = [-1,0,1,2,-1,-4] → Output: " + sol.threeSum(new int[]{-1,0,1,2,-1,-4})); // [[-1,-1,2],[-1,0,1]]
        System.out.println("Input: nums = [] → Output: " + sol.threeSum(new int[]{})); // []
        System.out.println("Input: nums = [0,0,0] → Output: " + sol.threeSum(new int[]{0,0,0})); // []

        System.out.println("done");
    }
}
