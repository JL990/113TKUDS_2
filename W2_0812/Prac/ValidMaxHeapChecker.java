package W2_0812.Prac;

import java.util.Arrays;

public class ValidMaxHeapChecker {

    // 檢查是否為 Max Heap
    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;

        // 空陣列或只有一個元素 -> 一定符合
        if (n <= 1) return true;

        // 遍歷所有非葉節點
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;  // 左子節點
            int right = 2 * i + 2; // 右子節點

            // 左子節點檢查
            if (left < n && arr[i] < arr[left]) {
                System.out.println("索引 " + left + " 的值 " + arr[left] + " 大於父節點 " + arr[i]);
                return false;
            }

            // 右子節點檢查
            if (right < n && arr[i] < arr[right]) {
                System.out.println("索引 " + right + " 的值 " + arr[right] + " 大於父節點 " + arr[i]);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65},  // true
            {100, 90, 80, 95, 60, 75, 65},  // false
            {50},                           // true
            {}                              // true
        };

        for (int[] test : testCases) {
            System.out.println("檢查陣列: " + Arrays.toString(test));
            boolean result = isValidMaxHeap(test);
            System.out.println("結果: " + result);
            System.out.println();
        }
    }
}
