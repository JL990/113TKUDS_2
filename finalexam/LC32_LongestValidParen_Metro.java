package finalexam;

import java.util.ArrayDeque;



public class LC32_LongestValidParen_Metro {
    
    class Solution {
        public int longestValidParentheses(String s) {
            int maxLen = 0; // 最長有效括號長度
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.push(-1); // 初始化堆疊，方便計算長度

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                // 若遇到右括號且堆疊頂端為左括號，則配對成功
                if (ch == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                    stack.pop(); // 移除配對的左括號位置
                } else {
                    stack.push(i); // 記錄括號位置
                }
                // 計算目前有效括號長度
                maxLen = Math.max(maxLen, i - stack.peek());
            }
            return maxLen;
        }
    }
    public static void main(String[] args) {
        Solution sol = new LC32_LongestValidParen_Metro().new Solution();

        // 測試案例
        System.out.println("Input: s = '(()' → Output: " + sol.longestValidParentheses("(()")); // 2
        System.out.println("Input: s = ')()())' → Output: " + sol.longestValidParentheses(")()())")); // 4
        System.out.println("Input: s = '' → Output: " + sol.longestValidParentheses("")); // 0

        System.out.println("done");
    }
}
