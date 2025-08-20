package midterm;

import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws IOException {
        System.out.println("input: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] times = new int[n];

        // 讀入並轉換成分鐘數
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            times[i] = toMinutes(s);
        }

        String queryStr = br.readLine().trim();
        int query = toMinutes(queryStr);

        // 使用二分搜尋找「第一個大於 query 的時間」
        int idx = binarySearch(times, query);

        if (idx == -1) {
            System.out.println("No bike");
        } else {
            System.out.println("output:"+toHHMM(times[idx]));
        }
    }

    // 將 HH:mm 轉為自 00:00 起的分鐘數
    static int toMinutes(String s) {
        String[] parts = s.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }

    // 將分鐘數轉回 HH:mm 格式（補零）
    static String toHHMM(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // 二分搜尋：找第一個大於 query 的元素索引
    static int binarySearch(int[] arr, int query) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > query) {
                ans = mid;
                right = mid - 1; // 繼續往左找，確保是「第一個大於」
            } else {
                left = mid + 1; // arr[mid] ≤ query，往右找
            }
        }
        return ans;
    }
}

/*
時間複雜度：
- 轉換 n 個時間：O(n)
- 二分搜尋：O(log n)
總和：O(n + log n) ≈ O(n)，在 n ≤ 200 時非常快。

空間複雜度：O(n)（儲存時間陣列）
*/
