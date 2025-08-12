package W2_0812.Prac;

import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;

public class KthSmallestElement{

    // 方法1：用大小為 K 的 Max Heap
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大值，保持大小為 k
            }
        }
        return maxHeap.peek(); // 堆頂就是第 k 小
    }

    // 方法2：用 Min Heap 提取 K 次
    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {
            minHeap.add(num);
        }

        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    // 測試
    public static void main(String[] args) {
        int[][] arrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < arrays.length; i++) {
            int[] arr = arrays[i];
            int k = ks[i];

            int ans1 = kthSmallestUsingMaxHeap(arr, k);
            int ans2 = kthSmallestUsingMinHeap(arr, k);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);

            System.out.println("陣列：" + Arrays.toString(arr) + ", K=" + k);
            System.out.println("方法1(Max Heap) -> 答案：" + ans1);
            System.out.println("方法2(Min Heap) -> 答案：" + ans2);
            System.out.println("排序後為：" + Arrays.toString(sorted));
            System.out.println();
        }
    }
}
