package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class lt_06_ZigzagConversion {
    static class Solution {
        public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
                return s;
            }

            int idx = 0, d = 1;
            List<Character>[] rows = new ArrayList[numRows];
            for (int i = 0; i < numRows; i++) {
                rows[i] = new ArrayList<>();
            }

            for (char c : s.toCharArray()) {
                rows[idx].add(c);
                if (idx == 0) {
                    d = 1;
                } else if (idx == numRows - 1) {
                    d = -1;
                }
                idx += d;
            }

            StringBuilder result = new StringBuilder();
            for (List<Character> row : rows) {
                for (char c : row) {
                    result.append(c);
                }
            }

            return result.toString();        
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("Input: PAYPALISHIRING, 3 → Output: " + sol.convert("PAYPALISHIRING", 3)); 
        // 預期 "PAHNAPLSIIGYIR"

        System.out.println("Input: PAYPALISHIRING, 4 → Output: " + sol.convert("PAYPALISHIRING", 4)); 
        // 預期 "PINALSIGYAHRPI"

        System.out.println("Input: A, 1 → Output: " + sol.convert("A", 1)); 
        // 預期 "ACBD"

        System.out.println("done");
    }
}
