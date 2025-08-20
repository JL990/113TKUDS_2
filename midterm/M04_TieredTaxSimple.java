package midterm;

import java.util.*;

public class M04_TieredTaxSimple {

    // 計算單筆收入稅額
    // 時間複雜度: O(1)，因為最多檢查 4 個區間
    static int calcTax(int income) {
        int tax = 0;
        if (income <= 120000) {
            tax = (int)(income * 0.05);
        } else if (income <= 500000) {
            tax = (int)(120000 * 0.05
                    + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = (int)(120000 * 0.05
                    + (500000 - 120000) * 0.12
                    + (income - 500000) * 0.20);
        } else {
            tax = (int)(120000 * 0.05
                    + (500000 - 120000) * 0.12
                    + (1000000 - 500000) * 0.20
                    + (income - 1000000) * 0.30);
        }
        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int totalTax = 0;
        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = calcTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }

        int avg = totalTax / n;
        System.out.println("Average: " + avg);
    }
}
