package midterm;

import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int idx; // 紀錄輸入順序 (用來當作平手時的 tie-breaker)

        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("input: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int K = Integer.parseInt(first[1]);

        // 讀取輸入
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            String name = parts[0];
            int qty = Integer.parseInt(parts[1]);
            items[i] = new Item(name, qty, i);
        }

        // Min-Heap (依 qty 小到大；若數值相同，以輸入順序 idx 判斷穩定性)
        PriorityQueue<Item> heap = new PriorityQueue<>(new Comparator<Item>() {
            public int compare(Item a, Item b) {
                if (a.qty != b.qty) return a.qty - b.qty; // qty 小的優先
                return b.idx - a.idx; // 輸入順序晚的先被移除
            }
        });

        // 維護大小為 K 的 Min-Heap
        for (Item it : items) {
            heap.offer(it);
            if (heap.size() > K) heap.poll(); // 保持只有 K 筆
        }

        // 取出 heap 中元素 (升序)，轉成高到低排序
        List<Item> result = new ArrayList<>(heap);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty; // qty 高到低
            return a.idx - b.idx; // 相同數量依輸入順序
        });

        // 輸出結果
        System.out.println("output:");
        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
複雜度分析：
1. 讀取 n 筆資料：O(n)
2. 對每筆資料進行 heap 操作 (插入 + 可能的移除)：O(log K)
   → 總計 O(n log K)
3. 最後對 K 筆資料排序：O(K log K)
整體時間複雜度：O(n log K + K log K)
在 n ≤ 1000, K ≤ 50 的限制下，效能非常好。

空間複雜度：O(n)（儲存輸入紀錄）
*/