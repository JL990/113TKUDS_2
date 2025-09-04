package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class lt_39_CombinationSum {
    /**
     * 組合總和問題
     * 時間複雜度：O(2^n * k)，n 為 candidates 長度，k 為答案組合平均長度
     * 需嘗試所有可能組合（回溯法），每次遞迴最多分成兩種情況
     */
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();

            makeCombination(candidates, target, 0, new ArrayList<>(), 0, res);
            return res;        
        }

        /**
         * 回溯法產生所有組合
         * @param candidates 候選數字
         * @param target 目標總和
         * @param idx 當前處理到的索引
         * @param comb 當前組合
         * @param total 當前組合總和
         * @param res 最終答案列表
         */
        private void makeCombination(int[] candidates, int target, int idx, List<Integer> comb, int total, List<List<Integer>> res) {
            if (total == target) {
                res.add(new ArrayList<>(comb)); // 符合條件則加入答案
                return;
            }

            if (total > target || idx >= candidates.length) {
                return; // 超過目標或已遍歷完，結束遞迴
            }

            // 選擇當前數字
            comb.add(candidates[idx]);
            makeCombination(candidates, target, idx, comb, total + candidates[idx], res);
            comb.remove(comb.size() - 1); // 回溯

            // 不選擇當前數字，處理下一個
            makeCombination(candidates, target, idx + 1, comb, total, res);
        }    
    }
    public static void main(String[] args) {
        Solution sol = new lt_39_CombinationSum().new Solution();

        // 測試案例
        System.out.println("Input: candidates = [2,3,6,7], target = 7 → Output: " + sol.combinationSum(new int[]{2,3,6,7}, 7)); // [[7],[2,2,3]]
        System.out.println("Input: candidates = [2,3,5], target = 8 → Output: " + sol.combinationSum(new int[]{2,3,5}, 8)); // [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println("Input: candidates = [2], target = 1 → Output: " + sol.combinationSum(new int[]{2}, 1)); // []

        System.out.println("done");
    }
}
