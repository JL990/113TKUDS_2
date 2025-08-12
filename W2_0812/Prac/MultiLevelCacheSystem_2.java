package W2_0812.Prac;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 多層級 LRU + 頻率 + 成本最佳化快取系統
 *
 * 三層快取：
 * L1: 容量=2, 成本=1（速度快, 容量小）
 * L2: 容量=5, 成本=3（中速, 中容量）
 * L3: 容量=10, 成本=10（速度慢, 容量大）
 *
 * 策略：
 * - 頻繁存取的資料往上層移動
 * - 較少存取的資料往下層移動
 * - 每層用 PriorityQueue(最小堆) 維護最低分數的項目，以便淘汰或降級
 *
 * 分數計算公式：
 * score = freq / cost + recencyBonus
 * recencyBonus = 1 / (當前時間 - 最後存取時間 + 1)
 * 分數越高表示越該留在上層
 */
public class MultiLevelCacheSystem_2 {

    // 全域時間計數器，用來記錄每次存取的先後順序
    private static final AtomicLong TIME = new AtomicLong(0);

    /** 單筆快取資料結構 */
    static class CacheEntry {
        final int key;
        String value;
        long freq;        // 存取次數
        long lastAccess;  // 最後存取時間
        int level;        // 所屬層級 1, 2, 或 3

        CacheEntry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 0;
            this.lastAccess = now();
            this.level = level;
        }

        // 資料被存取時，更新頻率與最後存取時間
        void accessed() {
            freq++;
            lastAccess = now();
        }

        static long now() {
            return TIME.incrementAndGet();
        }

        @Override
        public String toString() {
            return String.format("%d:%s", key, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheEntry)) return false;
            CacheEntry other = (CacheEntry) o;
            return this.key == other.key;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(key);
        }
    }

    /** 單層快取結構 */
    static class LevelCache {
        final int levelId;     // 層級編號
        final int capacity;    // 容量上限
        final double cost;     // 存取成本
        final Map<Integer, CacheEntry> map = new HashMap<>();
        final PriorityQueue<CacheEntry> pq;

        LevelCache(int levelId, int capacity, double cost) {
            this.levelId = levelId;
            this.capacity = capacity;
            this.cost = cost;
            // 小根堆：分數最低的在堆頂，方便淘汰
            this.pq = new PriorityQueue<>(Comparator.comparingDouble(this::score));
        }

        // 計算快取項目的分數
        double score(CacheEntry e) {
            double recencyBonus = 1.0 / (Math.max(1, (TIME.get() - e.lastAccess)) + 1);
            return (e.freq / this.cost) + recencyBonus;
        }

        boolean contains(int key) {
            return map.containsKey(key);
        }

        CacheEntry getEntry(int key) {
            return map.get(key);
        }

        // 放入或更新項目
        void putEntry(CacheEntry entry) {
            if (map.containsKey(entry.key)) {
                pq.remove(entry);
            }
            map.put(entry.key, entry);
            pq.add(entry);
        }

        // 從本層移除項目
        void removeEntry(CacheEntry entry) {
            map.remove(entry.key);
            pq.remove(entry);
        }

        // 存取後更新 PriorityQueue 排序
        void updateEntryOnAccess(CacheEntry entry) {
            pq.remove(entry);
            pq.add(entry);
        }

        boolean isAtCapacity() {
            return map.size() >= capacity;
        }

        // 取得最低分的項目（不移除）
        CacheEntry pickLowestScore() {
            return pq.peek();
        }

        // 移除並回傳最低分的項目
        CacheEntry evictLowest() {
            CacheEntry e = pq.poll();
            if (e != null) {
                map.remove(e.key);
            }
            return e;
        }

        // 取得排序後的清單（高分在前）
        List<CacheEntry> snapshotOrderedByScoreDesc() {
            List<CacheEntry> list = new ArrayList<>(map.values());
            list.sort((a, b) -> Double.compare(score(b), score(a)));
            return list;
        }
    }

    /** 多層快取主控 */
    static class MultiLevel {
        final LevelCache L1;
        final LevelCache L2;
        final LevelCache L3;

        MultiLevel() {
            L1 = new LevelCache(1, 2, 1.0);
            L2 = new LevelCache(2, 5, 3.0);
            L3 = new LevelCache(3, 10, 10.0);
        }

        // 讀取資料
        public String get(int key) {
            CacheEntry e;
            if ((e = L1.getEntry(key)) != null) {
                e.accessed();
                L1.updateEntryOnAccess(e);
                return e.value;
            }
            if ((e = L2.getEntry(key)) != null) {
                e.accessed();
                promote(e, L2, L1);
                return e.value;
            }
            if ((e = L3.getEntry(key)) != null) {
                e.accessed();
                promote(e, L3, L2);
                return e.value;
            }
            return null;
        }

        // 寫入資料
        public void put(int key, String value) {
            CacheEntry e;
            if ((e = L1.getEntry(key)) != null) {
                e.value = value;
                e.accessed();
                L1.updateEntryOnAccess(e);
                return;
            }
            if ((e = L2.getEntry(key)) != null) {
                e.value = value;
                e.accessed();
                promote(e, L2, L1);
                return;
            }
            if ((e = L3.getEntry(key)) != null) {
                e.value = value;
                e.accessed();
                promote(e, L3, L2);
                return;
            }

            // 新資料直接放 L1
            CacheEntry newEntry = new CacheEntry(key, value, 1);
            newEntry.accessed();
            L1.putEntry(newEntry);
            if (L1.isAtCapacity()) {
                handleOverflow(L1);
            }
        }

        // 提升層級
        private void promote(CacheEntry e, LevelCache from, LevelCache to) {
            from.removeEntry(e);
            e.level = to.levelId;
            to.putEntry(e);
            if (to.isAtCapacity()) {
                handleOverflow(to);
            }
        }

        // 層級溢出處理（往下移）
        private void handleOverflow(LevelCache level) {
            while (level.map.size() > level.capacity) {
                CacheEntry victim = level.evictLowest();
                if (victim == null) break;
                if (level.levelId == 1) {
                    victim.level = 2;
                    L2.putEntry(victim);
                    if (L2.isAtCapacity()) {
                        handleOverflow(L2);
                    }
                } else if (level.levelId == 2) {
                    victim.level = 3;
                    L3.putEntry(victim);
                    if (L3.isAtCapacity()) {
                        handleOverflow(L3);
                    }
                }
            }
        }

        // 顯示快取狀態
        public void dumpState() {
            System.out.println("L1: " + L1.snapshotOrderedByScoreDesc());
            System.out.println("L2: " + L2.snapshotOrderedByScoreDesc());
            System.out.println("L3: " + L3.snapshotOrderedByScoreDesc());
        }
    }

    public static void main(String[] args) {
        MultiLevel cache = new MultiLevel();

        System.out.println("put(1, \"A\"); put(2, \"B\"); put(3, \"C\");");
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.dumpState();
        // 預期: L1: [2,3], L2: [1], L3: []

        System.out.println("\nget(1); get(1); get(2);");
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.dumpState();
        // 預期: L1: [1,2], L2: [3], L3: []

        System.out.println("\nput(4, \"D\"); put(5, \"E\"); put(6, \"F\");");
        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.dumpState();
        // 根據存取頻率與分數決定分佈
    }
}

