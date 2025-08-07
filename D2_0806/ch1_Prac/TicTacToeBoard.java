package D2_0806.ch1_Prac;

import java.util.Scanner;

public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // 初始化棋盤
    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // 顯示棋盤
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // 放置棋子
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            } else {
                System.out.println("位置已被佔用！");
            }
        } else {
            System.out.println("無效的位置！");
        }
        return false;
    }

    // 檢查是否有勝利者
    public boolean checkWin() {
        // 檢查行、列
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer &&
                 board[i][1] == currentPlayer &&
                 board[i][2] == currentPlayer) ||

                (board[0][i] == currentPlayer &&
                 board[1][i] == currentPlayer &&
                 board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // 檢查對角線
        if ((board[0][0] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][2] == currentPlayer) ||

            (board[0][2] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    // 檢查是否平手（棋盤填滿但沒人贏）
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    // 換下一位玩家
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // 主程式：執行遊戲
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeBoard game = new TicTacToeBoard();

        System.out.println("井字遊戲開始！");
        game.printBoard();

        while (true) {
            System.out.println("輪到玩家 " + game.getCurrentPlayer());
            System.out.print("請輸入行 (0-2): ");
            int row = scanner.nextInt();
            System.out.print("請輸入列 (0-2): ");
            int col = scanner.nextInt();

            if (game.placeMark(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("玩家 " + game.getCurrentPlayer() + " 獲勝！");
                    break;
                }

                if (game.isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                game.changePlayer();
            }
        }

        scanner.close();
    }
}
