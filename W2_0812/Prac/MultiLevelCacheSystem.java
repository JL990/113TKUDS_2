package W2_0812.Prac;

import java.util.*;

public class MultiLevelCacheSystem {
    static class CacheEntry {
        int key;
        String value;
        int freq;
        long timestamp;
        int level;  // 1, 2, 3

        public CacheEntry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
            this.level = level;
        }

        double score(int levelCost) {
            // 評分公式：頻率 / 成本 + 時間衰減 (可改)
            return (double) freq / levelCost + (1.0 / (System.nanoTime() - timestamp + 1));
        }
    }

    private Map<Integer, CacheEntry> map;
    private PriorityQueue<CacheEntry>[] levels; // index 0: L1, 1:L2, 2:L3
    private int[] capacity = {2, 5, 10};
    private int[] cost = {1, 3, 10};

    public MultiLevelCacheSystem() {
        map = new HashMap<>();
        levels = new PriorityQueue[3];
        for (int i = 0; i < 3; i++) {
            int levelIdx = i;
            levels[i] = new PriorityQueue<>((a, b) -> Double.compare(a.score(cost[levelIdx]), b.score(cost[levelIdx])));
        }
    }

    public String get(int key) {
        if (!map.containsKey(key)) return null;
        CacheEntry e = map.get(key);
        // 更新頻率和時間戳
        updateEntry(e, true);
        return e.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            CacheEntry e = map.get(key);
            e.value = value;
            updateEntry(e, false);
            return;
        }
        CacheEntry newEntry = new CacheEntry(key, value, 3); // 新資料先放L3
        map.put(key, newEntry);
        addToLevel(newEntry, 2);
        adjustLevels();
    }

    private void updateEntry(CacheEntry e, boolean isGet) {
        e.freq += isGet ? 1 : 0;
        e.timestamp = System.nanoTime();

        int curLevelIdx = e.level - 1;
        levels[curLevelIdx].remove(e);  // 移除重插（重新排序）
        levels[curLevelIdx].offer(e);

        adjustLevels();
    }

    private void addToLevel(CacheEntry e, int levelIdx) {
        e.level = levelIdx + 1;
        levels[levelIdx].offer(e);
    }

    private void removeFromLevel(CacheEntry e) {
        int idx = e.level - 1;
        levels[idx].remove(e);
    }

    private void adjustLevels() {
        // 由高層往低層調整空間不足問題
        for (int i = 0; i < 3; i++) {
            while (levels[i].size() > capacity[i]) {
                CacheEntry lowest = levels[i].poll();
                if (i == 2) {
                    // L3溢位，直接剔除最差的資料
                    map.remove(lowest.key);
                } else {
                    // 移往下一層
                    lowest.level = i + 2;
                    levels[i + 1].offer(lowest);
                }
            }
        }

        // 嘗試由低層往高層提升資料（頻率與成本比高者）
        for (int i = 2; i > 0; i--) {
            List<CacheEntry> promoteList = new ArrayList<>();
            for (CacheEntry e : levels[i]) {
                double curScore = e.score(cost[i]);
                double higherScore = e.freq / (double) cost[i - 1];
                if (higherScore > curScore) {
                    promoteList.add(e);
                }
            }
            for (CacheEntry e : promoteList) {
                levels[i].remove(e);
                addToLevel(e, i - 1);
            }
        }
    }

    // for debug
    public void printStatus() {
        for (int i = 0; i < 3; i++) {
            System.out.print("L" + (i + 1) + ": ");
            List<Integer> keys = new ArrayList<>();
            for (CacheEntry e : levels[i]) {
                keys.add(e.key);
            }
            System.out.println(keys);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println("初始放入：");
        cache.printStatus(); // L1: [], L2: [1,2], L3: [3]

        cache.get(1);
        Thread.sleep(1); // 確保時間戳更新
        cache.get(1);
        cache.get(2);
        System.out.println("多次存取後：");
        cache.printStatus(); // 1頻率高應移L1

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        System.out.println("放入更多資料：");
        cache.printStatus();
    }
}
