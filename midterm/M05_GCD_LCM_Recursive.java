package midterm;

import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) throws IOException {
        System.out.println("input: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split("\\s+");

        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);

        long g = gcd(a, b);
        long l = (a / g) * b;  // 先除後乘，避免溢位

        System.out.println("output:");
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴版歐幾里得
    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
複雜度分析：
1. gcd 遞迴演算法：
   - 每次遞迴計算 x % y，數值會快速縮小。
   - 時間複雜度 O(log(min(a, b)))。
2. LCM 計算 O(1)。
3. 總計時間複雜度：O(log(min(a, b)))
   空間複雜度：O(log(min(a, b)))（遞迴堆疊）

在 a, b ≤ 10^9 的情況下，效率非常好。
*/
