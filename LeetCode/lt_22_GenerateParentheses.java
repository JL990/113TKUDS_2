package LeetCode;
import java.util.*;

public class lt_22_GenerateParentheses {

    /**
     * 產生所有有效的括號組合
     * 時間複雜度：O(4^n / sqrt(n))，n 為括號對數
     * 主要為回溯法展開所有可能組合，並剪枝不合法情況
     */
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrack(res, new StringBuilder(), 0, 0, n);
            return res;        
        }

        /**
         * 回溯法產生所有有效括號組合
         * @param res 儲存結果的列表
         * @param curString 當前組合字串
         * @param openCount 已加入的左括號數量
         * @param closeCount 已加入的右括號數量
         * @param n 括號對數
         */
        private void backtrack(List<String> res, StringBuilder curString, int openCount, int closeCount, int n) {
            // 終止條件：字串長度達 2*n，表示組合完成
            if(curString.length() == 2 * n) {
                res.add(curString.toString());
                return;
            }

            // 選擇一：還有左括號可用時，加入左括號
            if(openCount < n) {
                curString.append("(");
                backtrack(res, curString, openCount + 1, closeCount, n);
                curString.deleteCharAt(curString.length() - 1); // 回溯
            }

            // 選擇二：右括號數量小於左括號時，加入右括號
            if(closeCount < openCount) {
                curString.append(")");
                backtrack(res, curString, openCount, closeCount + 1, n);
                curString.deleteCharAt(curString.length() - 1); // 回溯
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_22_GenerateParentheses().new Solution();

        // 測試案例
        System.out.println("Input: n = 3 → Output: " + sol.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]
        System.out.println("Input: n = 1 → Output: " + sol.generateParenthesis(1)); // ["()"]

        System.out.println("done");
    }
}
