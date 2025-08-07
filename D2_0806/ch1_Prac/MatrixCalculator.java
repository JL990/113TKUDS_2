package D2_0806.ch1_Prac;

public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("=== 矩陣 A ===");
        printMatrix(matrixA);
        System.out.println("=== 矩陣 B ===");
        printMatrix(matrixB);

        // 加法
        System.out.println("=== A + B ===");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        // 乘法 A * C
        System.out.println("=== A * C ===");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        printMatrix(product);

        // 轉置
        System.out.println("=== A 的轉置 ===");
        int[][] transposeA = transposeMatrix(matrixA);
        printMatrix(transposeA);

        // 最大與最小
        System.out.println("=== A 中最大與最小值 ===");
        int[] maxMin = findMaxMin(matrixA);
        System.out.println("最大值: " + maxMin[0]);
        System.out.println("最小值: " + maxMin[1]);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = a[i][j] + b[i][j];

        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            System.out.println("錯誤：無法相乘，A 的欄數需等於 B 的列數。");
            return new int[0][0];
        }

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += a[i][k] * b[k][j];

        return result;
    }

    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][i] = matrix[i][j];

        return result;
    }

    // 尋找最大與最小值
    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }

        return new int[]{max, min};
    }

    // 印出矩陣
    public static void printMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            System.out.println("(空矩陣)");
            return;
        }

        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
    }
}
