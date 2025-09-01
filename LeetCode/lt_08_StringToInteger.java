package LeetCode;

public class lt_08_StringToInteger {
    static class Solution {
        public int myAtoi(String s) {
            s = s.trim(); // Remove leading whitespace
            int sign = 1, i = 0;
            long res = 0; // Using long to handle overflow cases

            if (s.length() == 0) return 0;

            // Check for sign
            if (s.charAt(0) == '-') { sign = -1; i++; }
            else if (s.charAt(0) == '+') { i++; }

            // Process numerical characters
            while (i < s.length()) {
                char ch = s.charAt(i);
                if (ch < '0' || ch > '9') break; // Stop at non-numeric character

                res = res * 10 + (ch - '0'); // Convert char to number
                if (sign * res > Integer.MAX_VALUE) return Integer.MAX_VALUE; // Handle overflow
                if (sign * res < Integer.MIN_VALUE) return Integer.MIN_VALUE;

                i++;
            }
            return (int) (sign * res);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("Input: \"42\" → Output: " + sol.myAtoi("42"));                     // 42
        System.out.println("Input: \"-42\" → Output: " + sol.myAtoi("-42"));             // -42
        System.out.println("Input: \"1337c0d3\" → Output: " + sol.myAtoi("1337c0d3")); // 1337
        System.out.println("Input: \"0-1\" → Output: " + sol.myAtoi("0-1"));     // 0
        System.out.println("Input: \"words and 987\" → Output: " + sol.myAtoi("words and 987"));       // -2147483648

        System.out.println("done");
    }
    
}
