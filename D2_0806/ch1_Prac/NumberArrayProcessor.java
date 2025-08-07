package D2_0806.ch1_Prac;

import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {4, 5, 6, 5, 3, 4, 7, 4};
        int[] array2 = {1, 3, 5, 7};
        int[] array3 = {2, 4, 4, 4, 6, 6, 6, 6, 9};

        // 1. 移除重複元素
        System.out.println("原始陣列（含重複）: " + Arrays.toString(array1));
        int[] unique = removeDuplicates(array1);
        System.out.println("移除重複後: " + Arrays.toString(unique));

        // 2. 合併已排序陣列
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8, 10};
        System.out.println("合併已排序陣列:");
        int[] merged = mergeSortedArrays(sorted1, sorted2);
        System.out.println(Arrays.toString(merged));

        // 3. 找出出現頻率最高的元素
        System.out.println("頻率最高的元素:");
        int mostFrequent = findMostFrequent(array3);
        System.out.println("最常出現的數字是: " + mostFrequent);

        // 4. 將陣列分割成兩個子陣列
        System.out.println("分割陣列:");
        int[] original = {10, 20, 30, 40, 50, 60, 70};
        int[][] split = splitArray(original);
        System.out.println("子陣列1: " + Arrays.toString(split[0]));
        System.out.println("子陣列2: " + Arrays.toString(split[1]));
    }

    // 1. 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>(); // 保留原順序
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 3. 找出出現頻率最高的元素
    public static int findMostFrequent(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxCount = 0;
        int mostFrequent = arr[0];

        for (int num : arr) {
            int count = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, count);

            if (count > maxCount) {
                maxCount = count;
                mostFrequent = num;
            }
        }

        return mostFrequent;
    }

    // 4. 將陣列分割為兩個子陣列（大小相等或相近）
    public static int[][] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[][] result = new int[2][];

        result[0] = Arrays.copyOfRange(arr, 0, mid);
        result[1] = Arrays.copyOfRange(arr, mid, arr.length);

        return result;
    }
}
