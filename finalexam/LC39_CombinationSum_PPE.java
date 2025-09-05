package finalexam;
import java.util.*;


public class LC39_CombinationSum_PPE {
    
    static class Solution {
        /**
         * 回溯法求所有組合總和
         * 時間複雜度：O(2^n * k)，n 為候選數長度，k 為平均組合長度
         */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(candidates, target, 0, new ArrayList<>(), result);
            return result;
        }

        /**
         * 回溯法產生所有組合
         * @param candidates 候選數字
         * @param target 目標總和
         * @param start 當前處理到的索引
         * @param current 當前組合
         * @param result 最終答案列表
         */
        private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
            if (target == 0) {
                List<Integer> comb = new ArrayList<>(current);
                Collections.sort(comb); // 題目要求升序
                result.add(comb);
                return;
            }
            if (target < 0) {
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                current.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i, current, result); // 可重複使用同一數字
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) candidates[i] = sc.nextInt();
        Solution sol = new Solution();
        List<List<Integer>> ans = sol.combinationSum(candidates, target);
        // 每行一個升序組合
        for (List<Integer> comb : ans) {
            Collections.sort(comb); // 確保升序
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i < comb.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
