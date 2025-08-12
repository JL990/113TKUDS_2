package W2_0812.Prac;

import java.util.*;

public class SlidingWindowMedian {

    // 最大堆（存比較小的一半數字）
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // 最小堆（存比較大的一半數字）
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // 用來延遲刪除（因為 PriorityQueue 不支援 O(log n) 隨機刪除）
    private Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize = 0; // maxHeap 有效元素數
    private int largeSize = 0; // minHeap 有效元素數

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // 新增元素
            addNum(nums[i]);
            if (i >= k) {
                // 移除超出視窗的元素
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                // 計算中位數
                result[i - k + 1] = getMedian(k);
            }
        }

        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            smallSize++;
        } else {
            minHeap.offer(num);
            largeSize++;
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            smallSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            largeSize--;
            if (num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    // 保持兩個堆的大小平衡
    private void balanceHeaps() {
        if (smallSize > largeSize + 1) {
            minHeap.offer(maxHeap.poll());
            smallSize--;
            largeSize++;
            prune(maxHeap);
        } else if (smallSize < largeSize) {
            maxHeap.offer(minHeap.poll());
            smallSize++;
            largeSize--;
            prune(minHeap);
        }
    }

    // 清理延遲刪除的元素
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        int k1 = 3;
        System.out.println("陣列：" + Arrays.toString(nums1) + ", K=" + k1);
        System.out.println("輸出：" + Arrays.toString(swm.medianSlidingWindow(nums1, k1)));
        System.out.println();

        int[] nums2 = {1,2,3,4};
        int k2 = 2;
        System.out.println("陣列：" + Arrays.toString(nums2) + ", K=" + k2);
        System.out.println("輸出：" + Arrays.toString(swm.medianSlidingWindow(nums2, k2)));
    }
}
