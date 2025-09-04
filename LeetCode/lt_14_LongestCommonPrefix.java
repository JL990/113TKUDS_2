package LeetCode;

import java.util.Arrays;

public class lt_14_LongestCommonPrefix {
    /**
     * 求字串陣列的最長共同前綴
     * 時間複雜度：O(nlogn + m)，n 為字串數量，m 為最長字串長度
     * 主要花費在排序字串陣列（O(nlogn)），比較首尾字串（O(m)）
     */
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            Arrays.sort(strs); // 先將字串陣列排序
            String s1 = strs[0]; // 排序後的第一個字串
            String s2 = strs[strs.length-1]; // 排序後的最後一個字串
            int idx = 0;
            // 比較首尾字串的每個字元，直到不相同為止
            while(idx < s1.length() && idx < s2.length()){
                if(s1.charAt(idx) == s2.charAt(idx)){
                    idx++;
                } else {
                    break;
                }
            }
            // 回傳最長共同前綴
            return s1.substring(0, idx);
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_14_LongestCommonPrefix().new Solution();

        // 測試案例
        System.out.println("Input: strs = ['flower','flow','flight'] → Output: " + sol.longestCommonPrefix(new String[]{"flower","flow","flight"})); // "fl"
        System.out.println("Input: strs = ['dog','racecar','car'] → Output: " + sol.longestCommonPrefix(new String[]{"dog","racecar","car"})); // ""
        System.out.println("Input: strs = ['a'] → Output: " + sol.longestCommonPrefix(new String[]{"a"})); // "a"
        System.out.println("Input: strs = ['ab','a'] → Output: " + sol.longestCommonPrefix(new String[]{"ab","a"})); // "a"

        System.out.println("done");
    }
}
