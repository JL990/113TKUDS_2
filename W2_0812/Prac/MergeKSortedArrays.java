package W2_0812.Prac;

import java.util.*;

public class MergeKSortedArrays {

    // 節點類別：存值、來自哪個陣列、陣列中的哪個位置
    static class Node {
        int value;
        int arrayIndex;
        int elementIndex;

        public Node(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();

        // Min Heap 根據 Node.value 排序
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));

        // 初始化：把每個陣列的第一個元素丟進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.add(new Node(arrays[i][0], i, 0));
            }
        }

        // 從 heap 取出最小值，並將該陣列的下一個元素放進 heap
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                minHeap.add(new Node(arrays[current.arrayIndex][nextIndex], current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][][] testCases = {
            {{1,4,5}, {1,3,4}, {2,6}},
            {{1,2,3}, {4,5,6}, {7,8,9}},
            {{1}, {0}}
        };

        for (int[][] arrays : testCases) {
            List<Integer> merged = mergeKSortedArrays(arrays);
            System.out.println("輸入：" + Arrays.deepToString(arrays));
            System.out.println("輸出：" + merged);
            System.out.println();
        }
    }
}
