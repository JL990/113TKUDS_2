package midterm;

import java.util.*;

public class M12_MergeKTimeTables {

    static class Entry {
        int time;      // 時間
        int listId;    // 來自第幾個列表
        int idx;       // 該列表的索引

        Entry(int t, int l, int i) {
            time = t; listId = l; idx = i;
        }
    }

    public static void main(String[] args) {
        System.out.println("input: ");
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                temp.add(sc.nextInt());
            }
            lists.add(temp);
        }

        // Min-Heap (依 time)
        PriorityQueue<Entry> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.time, b.time)
        );

        // 初始化: 每條列表的第一個放入堆
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                pq.offer(new Entry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();

        // 合併
        while (!pq.isEmpty()) {
            Entry cur = pq.poll();
            result.add(cur.time);

            int nextIdx = cur.idx + 1;
            if (nextIdx < lists.get(cur.listId).size()) {
                pq.offer(new Entry(lists.get(cur.listId).get(nextIdx), cur.listId, nextIdx));
            }
        }

        // 輸出
        System.out.println("ouput: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}

