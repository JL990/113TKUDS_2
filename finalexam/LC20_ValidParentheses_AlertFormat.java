package finalexam;

import java.util.Stack;

public class LC20_ValidParentheses_AlertFormat {
    
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>(); // 用於存放左括號
            for (char ch : s.toCharArray()) {
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch); // 左括號入堆疊
                } else {
                    if (stack.isEmpty()) {
                        return false; // 沒有對應的左括號
                    }
                    char top = stack.pop(); // 取出堆疊頂端的左括號
                    // 檢查括號是否配對
                    if (ch == ')' && top != '(') {
                        return false;
                    }
                    if (ch == ']' && top != '[') {
                        return false;
                    }
                    if (ch == '}' && top != '{') {
                        return false;
                    }
                }
            }
            return stack.isEmpty(); // 堆疊必須為空才是有效括號
        }
    }
    public static void main(String[] args) {
        Solution sol = new LC20_ValidParentheses_AlertFormat().new Solution();

        // 測試案例
        System.out.println("Input: s = '()' → Output: " + sol.isValid("()")); // true
        System.out.println("Input: s = '()[]{}' → Output: " + sol.isValid("()[]{}")); // true
        System.out.println("Input: s = '(]' → Output: " + sol.isValid("(]")); // false
        System.out.println("Input: s = '([)]' → Output: " + sol.isValid("([)]")); // false
        System.out.println("Input: s = '{[]}' → Output: " + sol.isValid("{[]}")); // true

        System.out.println("done");
    }
}

