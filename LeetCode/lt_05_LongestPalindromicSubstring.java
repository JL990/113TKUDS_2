package LeetCode;

public class lt_05_LongestPalindromicSubstring {
    static class Solution {
    public String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int start = 0; start <= s.length() - length; start++) {
                if (check(start, start + length, s)) {
                    return s.substring(start, start + length);
                }
            }
        }

        return "";
    }

    private boolean check(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("Input: babad → Output: " + sol.longestPalindrome("babad")); // "bab" or "aba"
        System.out.println("Input: cbbd  → Output: " + sol.longestPalindrome("cbbd"));  // "bb"
        
        System.out.println("done");
    }
}

