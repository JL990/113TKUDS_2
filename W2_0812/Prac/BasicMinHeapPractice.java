package W2_0812.Prac;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicMinHeapPractice {

    // 用 ArrayList 模擬二元樹結構的 Min Heap
    private ArrayList<Integer> heap;

    // 建構子：初始化空的 heap
    public BasicMinHeapPractice() {
        heap = new ArrayList<>();
    }

    // 插入元素
    public void insert(int val) {
        heap.add(val); // 先加到最後
        heapifyUp(heap.size() - 1); // 往上調整
    }

    // 取出並返回最小值
    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap.get(0);

        // 將最後一個元素移到根，再向下調整
        int lastVal = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, lastVal);
            heapifyDown(0);
        }

        return min;
    }

    // 查看最小值（不刪除）
    public int getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // 回傳大小
    public int size() {
        return heap.size();
    }

    // 是否為空
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 往上調整
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // 往下調整
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // 交換
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    // 測試程式
    public static void main(String[] args) {
        BasicMinHeapPractice minHeap = new BasicMinHeapPractice();

        int[] nums = {15, 10, 20, 8, 25, 5};
        System.out.println("插入順序：" + Arrays.toString(nums));

        for (int num : nums) {
            minHeap.insert(num);
        }

        System.out.print("期望的 extractMin 順序：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractMin() + " ");
        }
    }
}
