package midterm;

import java.util.*;

public class M11_HeapSortWithTie {

    static class Student {
        int score;
        int index; // tie-breaking

        Student(int s, int i) {
            score = s;
            index = i;
        }
    }

    /**
     * 比較函數：
     * 回傳 true 若 a > b（Max-Heap 規則）
     * - 分數高者優先
     * - 若分數相同，index 較小者優先
     */
    static boolean greater(Student a, Student b) {
        if (a.score != b.score) return a.score > b.score;
        return a.index < b.index;
    }

    /**
     * Heapify: 從 idx 向下調整
     * 時間複雜度: O(log n)，因為最多下沉到樹高
     */
    static void heapify(Student[] arr, int n, int idx) {
        int largest = idx;
        int left = 2*idx + 1;
        int right = 2*idx + 2;

        if (left < n && greater(arr[left], arr[largest])) {
            largest = left;
        }
        if (right < n && greater(arr[right], arr[largest])) {
            largest = right;
        }

        if (largest != idx) {
            Student tmp = arr[idx];
            arr[idx] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    /**
     * Heap Sort 主程式
     * - 建堆 O(n)
     * - 取出最大並調整 O(n log n)
     * 總時間複雜度: O(n log n)
     * 空間複雜度: O(1)
     */
    static void heapSort(Student[] arr) {
        int n = arr.length;

        // 建立 Max-Heap
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐一取出最大值，放到尾部
        for (int i = n-1; i > 0; i--) {
            Student tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            arr[i] = new Student(score, i);
        }

        heapSort(arr);

        // 輸出結果 (遞增序)
        System.out.println("output: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score);
            if (i < n-1) System.out.print(" ");
        }
        System.out.println();
    }
}
