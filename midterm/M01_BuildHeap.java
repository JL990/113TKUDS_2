package midterm;

import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    static String type; // "max" 或 "min"
    static int n;
    static int[] heap;

    public static void main(String[] args) throws IOException {
        System.out.println("input: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        type = br.readLine().trim();
        n = Integer.parseInt(br.readLine().trim());
        heap = new int[n];

        String[] parts = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            heap[i] = Integer.parseInt(parts[i]);
        }

        buildHeap();

        // 輸出結果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(heap[i]);
            if (i < n - 1) sb.append(" ");
        }
        System.out.println("output: "+sb);
    }

    // 自底向上建堆
    static void buildHeap() {
        // 最後一個非葉節點是 (n/2) - 1
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    static void heapifyDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int swapIndex = i;

            if (type.equals("max")) {
                if (left < n && heap[left] > heap[swapIndex]) {
                    swapIndex = left;
                }
                if (right < n && heap[right] > heap[swapIndex]) {
                    swapIndex = right;
                }
            } else { // min-heap
                if (left < n && heap[left] < heap[swapIndex]) {
                    swapIndex = left;
                }
                if (right < n && heap[right] < heap[swapIndex]) {
                    swapIndex = right;
                }
            }

            if (swapIndex == i) break; // 已符合堆的性質

            // 交換
            int temp = heap[i];
            heap[i] = heap[swapIndex];
            heap[swapIndex] = temp;

            i = swapIndex; // 繼續往下檢查
        }
    }

    
}


/*
Build-Heap 自底向上演算法複雜度分析：

1. heapifyDown(i) 的時間複雜度 = O(h)，其中 h 為節點 i 的高度。
   - 高度越大，heapifyDown 所需的交換次數越多。
   - 葉節點不需要調整，樹越上層的節點調整次數才多。

2. Build-Heap 從 (n/2)-1 到 0 逐一呼叫 heapifyDown。
   - 高度為 0 的節點（葉子）不用動。
   - 高度為 1 的節點數量大約 n/2，heapifyDown 每次最多 O(1)。
   - 高度為 2 的節點數量大約 n/4，heapifyDown 每次最多 O(2)。
   - ...
   - 根節點只有 1 個，heapifyDown 最多 O(log n)。

3. 總計：
   Σ (節點數 × 對應高度)
   ≈ n × (1/2 + 1/4×2 + 1/8×3 + ... + 1/log n × log n)
   = O(n)

因此，自底向上建堆的整體時間複雜度為 **O(n)**，
遠優於逐一 insert 的 O(n log n)。
*/