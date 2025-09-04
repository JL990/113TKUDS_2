package LeetCode;

public class lt_36_ValidSudoku {
    /**
     * 檢查 9x9 數獨盤面是否有效
     * 時間複雜度：O(1)
     * 盤面大小固定，最多檢查 81 格，每格最多 3 次查詢
     */
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[][] rows = new boolean[9][9];   // 記錄每行是否出現過某數字
            boolean[][] cols = new boolean[9][9];   // 記錄每列是否出現過某數字
            boolean[][] boxes = new boolean[9][9];  // 記錄每個 3x3 子盒是否出現過某數字

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1'; // 將字元轉成 0~8 的索引
                        int boxIndex = (i / 3) * 3 + (j / 3); // 計算所在 3x3 子盒編號

                        // 若該數字已在行、列或子盒出現過，則盤面無效
                        if (rows[i][num] || cols[j][num] || boxes[boxIndex][num]) {
                            return false;
                        }

                        // 標記該數字已出現
                        rows[i][num] = cols[j][num] = boxes[boxIndex][num] = true;
                    }
                }
            }
            return true; // 所有檢查都通過則有效
        }
    }
    public static void main(String[] args) {
        Solution sol = new lt_36_ValidSudoku().new Solution();

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
        System.out.println("Input: board1 → Output: " + sol.isValidSudoku(board1)); // true

        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Input: board2 → Output: " + sol.isValidSudoku(board2)); // false

        System.out.println("done");
    }
}
