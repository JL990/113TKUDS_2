package LeetCode;

public class lt_13_RomantoInteger {
    
    static class Solution {
        /**
         * 將羅馬數字字串轉換為整數
         * 時間複雜度：O(n)，其中 n 為字串長度
         * 只需遍歷一次字串即可完成轉換
         * @param s 欲轉換的羅馬數字字串
         * @return 對應的整數
         */
        public int romanToInt(String s) {
            int ans = 0, num = 0; // ans: 最終結果, num: 當前字元代表的數值
            // 從字串尾端往前遍歷
            for (int i = s.length() - 1; i >= 0; i--) {
                // 根據羅馬字元取得對應數值
                switch (s.charAt(i)) {
                    case 'I':
                        num = 1;
                        break;
                    case 'V':
                        num = 5;
                        break;
                    case 'X':
                        num = 10;
                        break;
                    case 'L':
                        num = 50;
                        break;
                    case 'C':
                        num = 100;
                        break;
                    case 'D':
                        num = 500;
                        break;
                    case 'M':
                        num = 1000;
                        break;
                }
                // 判斷是否需要減去該數值（羅馬數字減法規則）
                if (4 * num < ans)
                    ans -= num;
                else
                    ans += num;
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 測試案例
        System.out.println("Input: s = 'III' → Output: " + sol.romanToInt("III")); // 3
        System.out.println("Input: s = 'IV' → Output: " + sol.romanToInt("IV")); // 4
        System.out.println("Input: s = 'IX' → Output: " + sol.romanToInt("IX")); // 9
        System.out.println("Input: s = 'LVIII' → Output: " + sol.romanToInt("LVIII")); // 58
        System.out.println("Input: s = 'MCMXCIV' → Output: " + sol.romanToInt("MCMXCIV")); // 1994

        System.out.println("done");
    }
}
