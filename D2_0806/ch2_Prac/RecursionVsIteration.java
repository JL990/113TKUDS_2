package D2_0806.ch2_Prac;

import java.util.Stack;

public class RecursionVsIteration {

    // ---------------------
    // 1. 計算二項式係數 C(n, k)
    // ---------------------
    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int maxK = Math.min(i, k);
            for (int j = 0; j <= maxK; j++) {
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    // ---------------------
    // 2. 陣列乘積
    // ---------------------
    public static int productRecursive(int[] arr, int index) {
        if (index >= arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    // ---------------------
    // 3. 元音字母計數
    // ---------------------
    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        return (isVowel(c) ? 1 : 0) + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if (isVowel(c)) count++;
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    // ---------------------
    // 4. 括號配對檢查
    // ---------------------
    public static boolean isBalancedRecursive(String str) {
        return checkBalanced(str, 0, 0);
    }

    private static boolean checkBalanced(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;

        char c = str.charAt(index);
        if (c == '(') return checkBalanced(str, index + 1, balance + 1);
        if (c == ')') return checkBalanced(str, index + 1, balance - 1);
        return checkBalanced(str, index + 1, balance);
    }

    public static boolean isBalancedIterative(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    // ---------------------
    // 主程式：效能比較
    // ---------------------
    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String testStr = "Recursion is awesome!";
        String brackets = "(((()())()))";

        System.out.println("=== 二項式係數 C(n,k) ===");
        long start = System.nanoTime();
        int resultR = binomialRecursive(n, k);
        long end = System.nanoTime();
        System.out.println("遞迴：" + resultR + "，耗時：" + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        int resultI = binomialIterative(n, k);
        end = System.nanoTime();
        System.out.println("迭代：" + resultI + "，耗時：" + (end - start) / 1_000_000 + " ms");

        System.out.println("\n=== 陣列乘積 ===");
        System.out.println("遞迴：" + productRecursive(arr, 0));
        System.out.println("迭代：" + productIterative(arr));

        System.out.println("\n=== 元音計數 ===");
        System.out.println("遞迴：" + countVowelsRecursive(testStr, 0));
        System.out.println("迭代：" + countVowelsIterative(testStr));

        System.out.println("\n=== 括號配對 ===");
        System.out.println("遞迴：" + isBalancedRecursive(brackets));
        System.out.println("迭代：" + isBalancedIterative(brackets));
    }
}
