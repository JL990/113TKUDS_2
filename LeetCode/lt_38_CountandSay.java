package LeetCode;

public class lt_38_CountandSay {
    /**
     * 計數與報數問題
     * 時間複雜度：O(n * m)，n 為層數，m 為每層字串長度
     * 每層都需遍歷前一層字串並生成新字串
     */
    class Solution {
        public String countAndSay(int n) {
            String res = "1"; // 初始字串
            for (int i = 1; i < n; i++) {
                StringBuilder temp = new StringBuilder();
                int count = 1;
                // 遍歷 res，統計連續相同字元的個數
                for (int j = 1; j < res.length(); j++) {
                    if (res.charAt(j) == res.charAt(j - 1)) {
                        count++; // 相同則累加
                    } else {
                        temp.append(count).append(res.charAt(j - 1)); // 不同則記錄
                        count = 1; // 重置計數
                    }
                }
                // 最後一段也要記錄
                temp.append(count).append(res.charAt(res.length() - 1));
                res = temp.toString(); // 更新 res 為新字串
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution sol = new lt_38_CountandSay().new Solution();

        // 測試案例
        System.out.println("Input: n = 1 → Output: " + sol.countAndSay(1)); // "1"
        System.out.println("Input: n = 4 → Output: " + sol.countAndSay(4)); // "1211"
        System.out.println("Input: n = 5 → Output: " + sol.countAndSay(5)); // "111221"

        System.out.println("done");
    }
}
