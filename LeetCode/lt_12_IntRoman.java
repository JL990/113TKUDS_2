package LeetCode;

public class lt_12_IntRoman {
    static class Solution {
        /**
         * 將整數轉換為羅馬數字
         * 時間複雜度：O(1)
         * 因為羅馬數字的組成是固定的，最多只會執行固定次數的迴圈。
         * @param num 欲轉換的整數（1 <= num <= 3999）
         * @return 對應的羅馬數字字串
         */
        public String intToRoman(int num) {
            // 定義所有羅馬數字的數值與符號
            final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            final String[] symbols = {"M", "CM", "D",  "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder sb = new StringBuilder(); // 用於組合羅馬數字

            // 依序檢查每個數值，將對應的羅馬符號加入字串
            for (int i = 0; i < values.length; ++i) {
                if (num == 0)
                    break; // 若已轉換完成則跳出
                while (num >= values[i]) {
                    sb.append(symbols[i]); // 加入對應羅馬符號
                    num -= values[i];      // 減去已處理的數值
                }
            }

            return sb.toString();        
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例
        System.out.println("Input: num = 3 → Output: " + sol.intToRoman(3)); // "III"
        System.out.println("Input: num = 4 → Output: " + sol.intToRoman(4)); // "IV"
        System.out.println("Input: num = 9 → Output: " + sol.intToRoman(9)); // "IX"
        System.out.println("Input: num = 58 → Output: " + sol.intToRoman(58)); // "LVIII"
        System.out.println("Input: num = 1994 → Output: " + sol.intToRoman(1994)); // "MCMXCIV"

        System.out.println("done");
    }
}
