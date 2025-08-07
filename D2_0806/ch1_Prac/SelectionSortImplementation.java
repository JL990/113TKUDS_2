package D2_0806.ch1_Prac;

import java.util.Arrays;

public class SelectionSortImplementation {

    // 比較與交換計數器
    static int selectionComparisons = 0;
    static int selectionSwaps = 0;

    static int bubbleComparisons = 0;
    static int bubbleSwaps = 0;

    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        int[] dataForBubble = Arrays.copyOf(data, data.length);

        System.out.println("原始陣列: " + Arrays.toString(data));
        System.out.println("\n--- 選擇排序 ---");
        selectionSort(data);

        System.out.println("\n比較次數: " + selectionComparisons);
        System.out.println("交換次數: " + selectionSwaps);

        System.out.println("\n--- 氣泡排序 ---");
        bubbleSort(dataForBubble);

        System.out.println("\n比較次數: " + bubbleComparisons);
        System.out.println("交換次數: " + bubbleSwaps);
    }

    // 選擇排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                selectionComparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
                selectionSwaps++;
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }
    }

    // 氣泡排序（用來比較效能）
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                bubbleComparisons++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    bubbleSwaps++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
            if (!swapped) break;
        }
    }

    // 交換函式
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
