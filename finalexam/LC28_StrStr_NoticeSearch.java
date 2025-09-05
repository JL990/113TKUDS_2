package finalexam;

public class LC28_StrStr_NoticeSearch {
    
    class Solution {
        public int strStr(String haystack, String needle) {
            if (needle.isEmpty()) return 0; // 若 needle 為空字串，回傳 0

            int hLen = haystack.length();
            int nLen = needle.length();

            // 遍歷 haystack，檢查每個子字串是否與 needle 相同
            for (int i = 0; i <= hLen - nLen; i++) {
                if (haystack.substring(i, i + nLen).equals(needle)) {
                    return i; // 找到匹配，回傳起始索引
                }
            }

            return -1; // 未找到匹配，回傳 -1
        }
    }

    public static void main(String[] args) {
        Solution sol = new LC28_StrStr_NoticeSearch().new Solution();

        // 測試案例
        System.out.println("Input: haystack = 'hello', needle = 'll' → Output: " + sol.strStr("hello", "ll")); // 2
        System.out.println("Input: haystack = 'aaaaa', needle = 'bba' → Output: " + sol.strStr("aaaaa", "bba")); // -1
        System.out.println("Input: haystack = '', needle = '' → Output: " + sol.strStr("", "")); // 0

        System.out.println("done");
    }       
}
