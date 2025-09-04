package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lt_40_CombinationSumII {
    /**
     * 組合總和 II（每個數字只能用一次，且不可重複組合）
     * 時間複雜度：O(2^n)，n 為 candidates 長度
     * 需嘗試所有可能組合（回溯法），並跳過重複元素
     */
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates); // 先排序，方便去除重複組合
            List<List<Integer>> res = new ArrayList<>();

            dfs(candidates, target, 0, new ArrayList<Integer>(), res);
            return res;
        }

        /**
         * 回溯法產生所有組合
         * @param candidates 候選數字（已排序）
         * @param target 目標總和
         * @param start 當前處理到的索引
         * @param comb 當前組合
         * @param res 最終答案列表
         */
        private void dfs(int[] candidates, int target, int start, List<Integer> comb, List<List<Integer>> res) {
            if (target < 0) {
                return; // 超過目標總和，結束遞迴
            }

            if (target == 0) {
                res.add(new ArrayList<Integer>(comb)); // 符合條件則加入答案
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 跳過同層重複元素，避免重複組合
                if (i > start && candidates[i] == candidates[i-1]) {
                    continue;
                }

                // 若當前數字已超過目標總和，後面也不用再嘗試
                if (candidates[i] > target) {
                    break;
                }

                comb.add(candidates[i]); // 選擇當前數字
                dfs(candidates, target - candidates[i], i + 1, comb, res); // 遞迴處理下一個
                comb.remove(comb.size() - 1); // 回溯
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_40_CombinationSumII().new Solution();

        // 測試案例
        System.out.println("Input: candidates = [10,1,2,7,6,1,5], target = 8 → Output: " + sol.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8)); // [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println("Input: candidates = [2,5,2,1,2], target = 5 → Output: " + sol.combinationSum2(new int[]{2,5,2,1,2}, 5)); // [[1,2,2],[5]]

        System.out.println("done");
    }
}
