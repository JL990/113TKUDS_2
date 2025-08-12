package W2_0812.Prac;

import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll();
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    public static Result maxMeetingTimeWithOneRoom(int[][] meetings) {
        if (meetings.length == 0) return new Result(0, new ArrayList<>());

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1]));
        int n = meetings.length;
        int[] dp = new int[n];
        int[] p = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = -1;
            parent[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (meetings[j][1] <= meetings[i][0]) {
                    p[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int duration = meetings[i][1] - meetings[i][0];
            int include = duration + (p[i] != -1 ? dp[p[i]] : 0);
            int exclude = (i > 0 ? dp[i - 1] : 0);

            if (include > exclude) {
                dp[i] = include;
                parent[i] = p[i];
            } else {
                dp[i] = exclude;
                parent[i] = (i > 0) ? parent[i - 1] : -1;
            }
        }

        // 回溯選擇的會議
        List<int[]> selected = new ArrayList<>();
        int idx = n - 1;
        while (idx >= 0) {
            if (parent[idx] == p[idx]) {  // 表示選了這個會議
                selected.add(meetings[idx]);
                idx = p[idx];
            } else {
                idx--;
            }
        }
        Collections.reverse(selected);

        return new Result(dp[n - 1], selected);
    }

    // 用來回傳結果和會議列表
    static class Result {
        int maxTime;
        List<int[]> meetings;

        public Result(int maxTime, List<int[]> meetings) {
            this.maxTime = maxTime;
            this.meetings = meetings;
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,30},{5,10},{15,20}};
        int[][] m2 = {{9,10},{4,9},{4,17}};
        int[][] m3 = {{1,5},{8,9},{8,9}};
        int[][] m4 = {{1,4},{2,3},{4,6}};

        System.out.println("會議：" + Arrays.deepToString(m1));
        System.out.println("答案：需要" + minMeetingRooms(m1) + "個會議室\n");

        System.out.println("會議：" + Arrays.deepToString(m2));
        System.out.println("答案：需要" + minMeetingRooms(m2) + "個會議室\n");

        System.out.println("會議：" + Arrays.deepToString(m3));
        System.out.println("答案：需要" + minMeetingRooms(m3) + "個會議室\n");

        System.out.println("如果只有1個會議室，會議：" + Arrays.deepToString(m4));
        Result res = maxMeetingTimeWithOneRoom(m4);
        System.out.print("最佳安排：選擇");
        for (int i = 0; i < res.meetings.size(); i++) {
            System.out.print(Arrays.toString(res.meetings.get(i)));
            if (i != res.meetings.size() - 1) System.out.print("和");
        }
        System.out.println("，總時間 = " + res.maxTime);
    }
}
