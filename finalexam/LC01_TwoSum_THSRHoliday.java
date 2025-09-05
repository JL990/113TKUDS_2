package finalexam;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    static class Solution {
        /**
         * 使用 HashMap 查表法，O(n) 時間複雜度
         * @param nums 整數陣列
         * @param target 目標值
         * @return 任意一組索引，若無解則回傳 {-1, -1}
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        Solution sol = new Solution();
        int[] result = sol.twoSum(nums, target);
        System.out.println(result[0] + " " + result[1]);
    }
}
