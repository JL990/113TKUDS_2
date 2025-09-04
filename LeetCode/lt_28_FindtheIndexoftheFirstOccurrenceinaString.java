package LeetCode;

public class lt_28_FindtheIndexoftheFirstOccurrenceinaString {
    /**
     * 找出 needle 在 haystack 中第一次出現的索引
     * 時間複雜度：O((n-m+1)*m)，n 為 haystack 長度，m 為 needle 長度
     * 需遍歷 haystack 並比較每個子字串
     */
    class Solution {
        public int strStr(String haystack, String needle) {
            // 逐一比較 haystack 的每個長度為 needle 的子字串
            for(int i = 0, j = needle.length(); j<=haystack.length(); i++,j++){
                if(haystack.substring(i,j).equals(needle)){
                    return i; // 找到則回傳索引
                }
            }
            return -1; // 沒找到則回傳 -1
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_28_FindtheIndexoftheFirstOccurrenceinaString().new Solution();

        // 測試案例
        System.out.println("Input: haystack = \"hello\", needle = \"ll\" → Output: " + sol.strStr("hello", "ll")); // 2
        System.out.println("Input: haystack = \"aaaaa\", needle = \"bba\" → Output: " + sol.strStr("aaaaa", "bba")); // -1
        System.out.println("Input: haystack = \"\", needle = \"\" → Output: " + sol.strStr("", "")); // 0

        System.out.println("done");
    }
}
