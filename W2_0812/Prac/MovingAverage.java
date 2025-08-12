package W2_0812.Prac;

import java.util.*;

public class MovingAverage {
    private int size;
    private Queue<Integer> window;

    // 中位數維護雙堆
    private PriorityQueue<Integer> maxHeap; // 小半部 最大堆
    private PriorityQueue<Integer> minHeap; // 大半部 最小堆

    // 延遲刪除的元素統計（中位數雙堆用）
    private Map<Integer, Integer> delayed;

    // 頻率映射 (TreeMap) 用於查詢最小/最大值
    private TreeMap<Integer, Integer> freqMap;

    private int smallSize;
    private int largeSize;
    private double sum;

    public MovingAverage(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.delayed = new HashMap<>();
        this.freqMap = new TreeMap<>();
        this.smallSize = 0;
        this.largeSize = 0;
        this.sum = 0.0;
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addNum(val);
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int old = window.poll();
            sum -= old;
            removeNum(old);
            freqMap.put(old, freqMap.get(old) - 1);
            if (freqMap.get(old) == 0) {
                freqMap.remove(old);
            }
        }

        return sum / window.size();
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

        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            smallSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            largeSize--;
            if (!minHeap.isEmpty() && num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

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

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) delayed.remove(num);
                heap.poll();
            } else {
                break;
            }
        }
    }

    public double getMedian() {
        if (window.isEmpty()) return 0.0;

        if (window.size() % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((long)maxHeap.peek() + (long)minHeap.peek()) / 2.0;
        }
    }

    public int getMin() {
        if (window.isEmpty()) throw new NoSuchElementException("Window is empty");
        return freqMap.firstKey();
    }

    public int getMax() {
        if (window.isEmpty()) throw new NoSuchElementException("Window is empty");
        return freqMap.lastKey();
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);

    System.out.printf("ma.next(1) = %.2f\n", ma.next(1));   // 1.00
    System.out.printf("ma.next(10) = %.2f\n", ma.next(10)); // 5.50
    System.out.printf("ma.next(3) = %.2f (1+10+3)/3\n", ma.next(3)); // 4.67
    System.out.printf("ma.next(5) = %.2f (10+3+5)/3\n", ma.next(5)); // 6.00

    System.out.printf("ma.getMedian() = %.1f\n", ma.getMedian()); // 5.0
    System.out.printf("ma.getMin() = %d\n", ma.getMin());         // 3
    System.out.printf("ma.getMax() = %d\n", ma.getMax());     
    }
}
