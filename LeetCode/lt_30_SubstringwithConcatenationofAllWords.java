package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lt_30_SubstringwithConcatenationofAllWords {
    /**
     * 找出所有子字串起始索引，使其為 words 中所有單字的連接
     * 時間複雜度：O(n * m * k)，n 為 s 長度，m 為 words 長度，k 為單字長度
     * 需遍歷每個可能起始點，並檢查每個單字是否出現
     */
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> result = new ArrayList<>();
            if (s == null || words == null || words.length == 0) return result;

            int wordLen = words[0].length(); // 單字長度
            int totalLen = wordLen * words.length; // 所有單字連接後的總長度
            int sLen = s.length();

            Map<String, Integer> wordCount = new HashMap<>(); // 統計每個單字出現次數
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            // 從每個可能的起始點開始滑動視窗
            for (int i = 0; i < wordLen; i++) {
                int left = i, right = i, count = 0;
                Map<String, Integer> window = new HashMap<>();

                while (right + wordLen <= sLen) {
                    String word = s.substring(right, right + wordLen); // 取出當前單字
                    right += wordLen;

                    if (wordCount.containsKey(word)) {
                        window.put(word, window.getOrDefault(word, 0) + 1);
                        count++;

                        // 若某單字出現次數超過要求，左指針右移
                        while (window.get(word) > wordCount.get(word)) {
                            String leftWord = s.substring(left, left + wordLen);
                            window.put(leftWord, window.get(leftWord) - 1);
                            left += wordLen;
                            count--;
                        }

                        // 若所有單字都出現一次，記錄起始索引
                        if (count == words.length) {
                            result.add(left);
                        }
                    } else {
                        window.clear(); // 非 words 單字，重置視窗
                        count = 0;
                        left = right;
                    }
                }
            }

            return result;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_30_SubstringwithConcatenationofAllWords().new Solution();

        // 測試案例
        System.out.println("Input: s = \"barfoothefoobarman\", words = [\"foo\",\"bar\"] → Output: " + sol.findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // [0,9]
        System.out.println("Input: s = \"wordgoodgoodgoodbestword\", words = [\"word\",\"good\",\"best\",\"word\"] → Output: " + sol.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"})); // []
        System.out.println("Input: s = \"barfoofoobarthefoobarman\", words = [\"bar\",\"foo\",\"the\"] → Output: " + sol.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"})); // [6,9,12]

        System.out.println("done");
    }
}
