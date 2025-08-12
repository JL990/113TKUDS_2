package W2_0812.Prac;

import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        if (prices == null || prices.length < 2 || K <= 0) return 0;

        // 儲存每段可獲利交易的利潤（Max Heap）
        PriorityQueue<Integer> profits = new PriorityQueue<>(Collections.reverseOrder());

        int i = 0;
        while (i < prices.length - 1) {
            // 找谷底（買入點）
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = prices[i];

            // 找峰頂（賣出點）
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = prices[i];

            if (sell > buy) {
                profits.offer(sell - buy);
            }
        }

        // 選擇前 K 大利潤
        int totalProfit = 0;
        while (K > 0 && !profits.isEmpty()) {
            totalProfit += profits.poll();
            K--;
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int[][] testPrices = {
            {2, 4, 1},
            {3, 2, 6, 5, 0, 3},
            {1, 2, 3, 4, 5}
        };
        int[] Ks = {2, 2, 2};
        int[] expected = {2, 7, 4};

        for (int t = 0; t < testPrices.length; t++) {
            int profit = maxProfit(testPrices[t], Ks[t]);
            System.out.println("價格：" + Arrays.toString(testPrices[t]) + ", K=" + Ks[t]);
            System.out.println("答案：" + profit + " (期望：" + expected[t] + ")");
            System.out.println();
        }
    }
}