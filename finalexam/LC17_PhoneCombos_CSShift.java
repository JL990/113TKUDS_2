package finalexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC17_PhoneCombos_CSShift {
    
    class Solution {
        public List<String> letterCombinations(String digits) {
            // 定義每個數字對應的字母
            List<String> ph = Arrays.asList("abc","def","ghi","jkl","mno","pqrs","tuv","wxyz");
            List<String> answer = new ArrayList<>();
            if(digits.equals("")) return answer; // 若輸入為空，直接回傳空列表
            cons(digits, 0, ph, "", answer); // 遞迴展開所有組合
            return answer;
        }

        /**
         * 遞迴展開所有可能組合
         * @param digits 輸入的數字字串
         * @param i 當前處理到的位數
         * @param ph 數字對應的字母列表
         * @param s 當前組合的字母
         * @param answer 最終答案列表
         */
        private void cons(String digits, int i, List<String> ph, String s, List<String> answer){
            if(i==digits.length()){
                answer.add(s); // 組合完成，加入答案
                return;
            }
            int number = digits.charAt(i) - '0'-2; // 計算對應的字母索引
            for(int j = 0;j<ph.get(number).length();j++){
                cons(digits, i+1, ph, s+ph.get(number).charAt(j),answer); // 遞迴處理下一位
            }
        }
    }
    public static void main(String[] args) {
        Solution sol = new LC17_PhoneCombos_CSShift().new Solution();

        // 測試案例
        System.out.println("Input: digits = '23' → Output: " + sol.letterCombinations("23")); // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println("Input: digits = '' → Output: " + sol.letterCombinations("")); // []
        System.out.println("Input: digits = '2' → Output: " + sol.letterCombinations("2")); // ["a","b","c"]

        System.out.println("done");
    }
}
