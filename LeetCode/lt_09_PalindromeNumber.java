package LeetCode;

public class lt_09_PalindromeNumber {
    static class Solution {
        public boolean isPalindrome(int x) {
            if(x<0){
                return false;
            }
            int rev = 0;
            int  num= x;

            while (num!= 0) {
                rev= rev*10 + num%10;
                num=num/10;
            }

            return (rev == x);
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("Input: 121 → Output: " + sol.isPalindrome(121));   // true
        System.out.println("Input: -121 → Output: " + sol.isPalindrome(-121)); // false
        System.out.println("Input: 10 → Output: " + sol.isPalindrome(10));     // false
        System.out.println("Input: -101 → Output: " + sol.isPalindrome(-101)); // false

        
        System.out.println("done"); 
    }
    
}
