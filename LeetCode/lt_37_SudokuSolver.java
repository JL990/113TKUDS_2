package LeetCode;

public class lt_37_SudokuSolver {
    /**
     * 解數獨問題（回溯法）
     * 時間複雜度：O(9^(n*n))，n 為盤面大小（本題 n=3, 盤面 9x9）
     * 最壞情況需嘗試所有可能填法，但實際上剪枝後遠小於此複雜度
     */
    class Solution {
        int n = 3; // 每個小方格的邊長
        int N = n * n; // 整個盤面大小
        int[][] rows = new int[N][N + 1];    // 記錄每行已填入的數字
        int[][] columns = new int[N][N + 1]; // 記錄每列已填入的數字
        int[][] boxes = new int[N][N + 1];   // 記錄每個 3x3 子盒已填入的數字
        char[][] board;                      // 數獨盤面
        boolean sudokuSolved = false;        // 是否已找到解

        /**
         * 判斷能否在 (row, col) 放入數字 d
         */
        public boolean couldPlace(int d, int row, int col) {
            int idx = (row / n) * n + col / n; // 計算所在 3x3 子盒編號
            return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
        }

        /**
         * 在 (row, col) 放入數字 d
         */
        public void placeNumber(int d, int row, int col) {
            int idx = (row / n) * n + col / n;
            rows[row][d]++;
            columns[col][d]++;
            boxes[idx][d]++;
            board[row][col] = (char)(d + '0');
        }

        /**
         * 移除 (row, col) 的數字 d（回溯用）
         */
        public void removeNumber(int d, int row, int col) {
            int idx = (row / n) * n + col / n;
            rows[row][d]--;
            columns[col][d]--;
            boxes[idx][d]--;
            board[row][col] = '.';
        }

        /**
         * 移動到下一格
         */
        public void placeNextNumbers(int row, int col) {
            if (row == N - 1 && col == N - 1) sudokuSolved = true; // 最後一格，完成
            else if (col == N - 1) backtrack(row + 1, 0); // 換到下一行
            else backtrack(row, col + 1); // 換到下一列
        }

        /**
         * 回溯法填數獨
         */
        public void backtrack(int row, int col) {
            if (board[row][col] == '.') {
                for (int d = 1; d <= 9; d++) {
                    if (couldPlace(d, row, col)) {
                        placeNumber(d, row, col);
                        placeNextNumbers(row, col);
                        if (!sudokuSolved) removeNumber(d, row, col); // 回溯
                    }
                }
            } else placeNextNumbers(row, col); // 已有數字，直接下一格
        }

        /**
         * 主函式：解數獨
         * @param board 9x9 數獨盤面
         */
        public void solveSudoku(char[][] board) {
            this.board = board;
            // 初始化已填入的數字
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (board[i][j] != '.') placeNumber(Character.getNumericValue(board[i][j]), i, j);
            backtrack(0, 0); // 從左上角開始回溯
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_37_SudokuSolver().new Solution();

        // 測試案例
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        sol.solveSudoku(board1);
        System.out.println("Solved Sudoku Board:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board1[i][j] + " ");
            }
            System.out.println();
        }
    }
}
