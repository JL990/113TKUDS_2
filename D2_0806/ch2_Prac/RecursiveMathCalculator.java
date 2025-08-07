package D2_0806.ch2_Prac;

public class RecursiveMathCalculator {

    // 組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n <= 1) return 1;

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 漢諾塔移動步數
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 判斷回文數（整數版）
    public static boolean isPalindrome(int number) {
        return isPalindromeHelper(Integer.toString(number), 0);
    }

    private static boolean isPalindromeHelper(String str, int index) {
        int len = str.length();
        if (index >= len / 2) return true;
        if (str.charAt(index) != str.charAt(len - 1 - index)) return false;
        return isPalindromeHelper(str, index + 1);
    }

    // 測試主程式
    public static void main(String[] args) {
        // 組合數
        int n = 5, k = 2;
        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));

        // 卡塔蘭數
        int c = 4;
        System.out.println("Catalan(" + c + ") = " + catalan(c));

        // 漢諾塔步數
        int disks = 3;
        System.out.println("Hanoi moves for " + disks + " disks = " + hanoiMoves(disks));

        // 回文數判斷
        int num1 = 12321;
        int num2 = 12345;
        System.out.println(num1 + " 是否為回文數？ " + isPalindrome(num1));
        System.out.println(num2 + " 是否為回文數？ " + isPalindrome(num2));
    }
}
