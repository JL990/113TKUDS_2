package LeetCode;

public class lt_29_DivideTwoIntegers {
    /**
     * 不使用乘法、除法和 mod 運算子，實現兩整數相除
     * 時間複雜度：O(log(n))，n 為被除數的絕對值
     * 主要利用位移運算加速減法，類似二分搜尋
     */
    class Solution {
        public int divide(int dividend, int divisor) {
            // 處理溢位情況
            if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
            long dvd = Math.abs((long)dividend); // 轉成 long 並取絕對值，避免溢位
            long dvs = Math.abs((long)divisor);
            long quotient = 0;

            // 透過位移運算加速減法
            while(dvd >= dvs) {
                long temp = dvs , multiple = 1;
                // 將除數倍增直到超過被除數
                while(dvd >= (temp << 1)) {
                    temp <<= 1;
                    multiple <<= 1;
                }
                dvd -= temp; // 減去最大可倍增的除數
                quotient += multiple; // 累加商
            }        
            // 判斷正負號
            if ((dividend < 0) ^ (divisor < 0)) return (int)-quotient;
            return (int)quotient;
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_29_DivideTwoIntegers().new Solution();

        // 測試案例
        System.out.println("Input: dividend = 10, divisor = 3 → Output: " + sol.divide(10, 3)); // 3
        System.out.println("Input: dividend = 7, divisor = -3 → Output: " + sol.divide(7, -3)); // -2
        System.out.println("Input: dividend = 0, divisor = 1 → Output: " + sol.divide(0, 1)); // 0
        System.out.println("Input: dividend = 1, divisor = 1 → Output: " + sol.divide(1, 1)); // 1
        System.out.println("Input: dividend = -2147483648, divisor = -1 → Output: " + sol.divide(-2147483648, -1)); // 2147483647

        System.out.println("done");
    }
}
