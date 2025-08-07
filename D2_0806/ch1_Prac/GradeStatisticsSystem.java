package D2_0806.ch1_Prac;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int total = 0;
        int max = scores[0];
        int min = scores[0];

        // 等第統計
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;

            // 統計等第
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        double average = total / (double)scores.length;

        // 統計高於平均的人數
        int countAboveAverage = 0;
        for (int score : scores) {
            if (score > average) countAboveAverage++;
        }

        // 列印報表
        System.out.println("=== 成績報表 ===");
        System.out.println("原始成績：");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println("\n--------------");
        System.out.printf("平均分：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("高於平均人數：" + countAboveAverage);
        System.out.println("等第統計：");
        System.out.println("A (90-100): " + countA + " 人");
        System.out.println("B (80-89): " + countB + " 人");
        System.out.println("C (70-79): " + countC + " 人");
        System.out.println("D (60-69): " + countD + " 人");
        System.out.println("F (<60): " + countF + " 人");
    }
}
