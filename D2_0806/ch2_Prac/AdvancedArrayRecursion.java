package D2_0806.ch2_Prac;

import java.util.Arrays;

public class AdvancedArrayRecursion {

    // 1. 遞迴實作快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];  // 使用最後一個元素作為基準
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 2. 遞迴合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeRecursive(a, b, 0, 0);
    }

    private static int[] mergeRecursive(int[] a, int[] b, int i, int j) {
        if (i == a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        if (j == b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }

        if (a[i] < b[j]) {
            int[] rest = mergeRecursive(a, b, i + 1, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = mergeRecursive(a, b, i, j + 1);
            return prepend(b[j], rest);
        }
    }

    private static int[] prepend(int val, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = val;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    // 3. 遞迴尋找第 k 小元素（Quickselect）
    public static int kthSmallest(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k 超出範圍");
        }
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    // 4. 遞迴檢查是否存在子序列總和為 target
    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index >= arr.length || target < 0) return false;

        // 選或不選當前元素
        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }

    // 主程式測試
    public static void main(String[] args) {
        int[] arr = {9, 2, 7, 4, 1, 6};
        System.out.println("原始陣列：" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序後：" + Arrays.toString(arr));

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        int[] merged = mergeSortedArrays(a, b);
        System.out.println("合併結果：" + Arrays.toString(merged));

        int[] data = {7, 2, 1, 6, 3};
        int k = 3;
        int kth = kthSmallest(data.clone(), k);
        System.out.println("第 " + k + " 小的元素是：" + kth);

        int[] subsetArray = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子序列總和為 " + target + "？" + subsetSum(subsetArray, 0, target));
    }
}
