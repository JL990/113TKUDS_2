package midterm;

import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws IOException {
        System.out.println("inpupt: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 轉小寫並清洗：只保留 a–z
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        String cleaned = sb.toString();
        boolean isPal = isPalindrome(cleaned, 0, cleaned.length() - 1);

        System.out.println("output:");
        System.out.println(isPal ? "Yes" : "No");
    }

    // 遞迴判斷回文
    static boolean isPalindrome(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindrome(s, left + 1, right - 1);
    }
}

/*
複雜度分析：
1. 清洗字串：走訪一次 O(n)，空間 O(n)。
2. 回文檢查：最多比較 n/2 次，時間 O(n)，遞迴深度 O(n)。
   → 在 n ≤ 10^5 時可行，但若擔心遞迴過深，可改雙指標迴圈。

總體時間複雜度：O(n)
空間複雜度：O(n)（清洗後字串；若用雙指標檢查原字串可降為 O(1)）
*/