package W2_0812.Prac;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;

public class PriorityQueueWithHeap {

    // 任務類別
    static class Task {
        String name;
        int priority;
        long timestamp; // 用來確保同優先級時，先加入的先執行

        public Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return String.format("%s (優先級: %d)", name, priority);
        }
    }

    // 比較器：先比較 priority（大優先），再比較 timestamp（小先）
    private Comparator<Task> taskComparator = (a, b) -> {
        if (b.priority != a.priority) {
            return Integer.compare(b.priority, a.priority); // 大優先級先
        }
        return Long.compare(a.timestamp, b.timestamp); // 先加入的先
    };

    private PriorityQueue<Task> pq;
    private long counter; // 用於生成 timestamp

    public PriorityQueueWithHeap() {
        pq = new PriorityQueue<>(taskComparator);
        counter = 0;
    }

    // 添加任務
    public void addTask(String name, int priority) {
        pq.add(new Task(name, priority, counter++));
    }

    // 執行優先級最高的任務
    public Task executeNext() {
        if (pq.isEmpty()) {
            System.out.println("沒有任務可執行");
            return null;
        }
        return pq.poll();
    }

    // 查看下一個要執行的任務
    public Task peek() {
        return pq.peek();
    }

    // 修改任務優先級（需要重建 Heap）
    public void changePriority(String name, int newPriority) {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean found = false;

        // 先取出所有任務
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name) && !found) {
                t.priority = newPriority;
                found = true;
            }
            tasks.add(t);
        }

        // 全部重新加回去
        pq.addAll(tasks);

        if (!found) {
            System.out.println("找不到任務：" + name);
        }
    }

    // 測試程式
    public static void main(String[] args) {
        PriorityQueueWithHeap taskQueue = new PriorityQueueWithHeap();

        // 添加任務
        taskQueue.addTask("備份", 1);
        taskQueue.addTask("緊急修復", 5);
        taskQueue.addTask("更新", 3);

        System.out.println("下一個任務: " + taskQueue.peek());

        System.out.println("執行順序應該是：緊急修復 → 更新 → 備份");
        while (taskQueue.peek() != null) {
            System.out.println("執行: " + taskQueue.executeNext());
        }

        // 測試修改優先級
        taskQueue.addTask("測試1", 2);
        taskQueue.addTask("測試2", 4);
        taskQueue.changePriority("測試1", 6);

        System.out.println("\n修改優先級後，下一個應該是測試1");
        while (taskQueue.peek() != null) {
            System.out.println("執行: " + taskQueue.executeNext());
        }
    }
}
