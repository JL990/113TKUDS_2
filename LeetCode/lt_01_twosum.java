package LeetCode;
import java.util.HashMap;
import java.util.Arrays; // 用來把結果陣列轉字串

public class lt_01_twosum{
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[]{i, j};
                    }
                }
            }
            // If no valid pair is found, return an empty array instead of null
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = sol.twoSum(nums, target);
        System.out.println("Result: " + Arrays.toString(result));
    }
}