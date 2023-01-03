package u5pp1;

public class Connect4 {

    public static final int RED_WIN = 0;
    public static final int BLACK_WIN = 1;
    public static final int NO_WINNER = 2;
    public static final int BOTH_WIN = 3;

    public static final int RED = 1;
    public static final int BLACK = -1;
    public static final int EMPTY = 0;

    public static boolean isFull(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBoardValid(int[][] board) {
        for (int c = 0; c < board[0].length; c++) {
            boolean foundAPiece = false;
            for (int r = 0; r < board.length; r++) {

                if (foundAPiece && board[r][c] == EMPTY) {
                    return false;
                }

                if (board[r][c] != 0) {
                    foundAPiece = true;
                }
            }
        }
        return true;
    }

    public static int getWinner(int[][] board) {
        boolean redWins = doesColorWin(board, RED);
        boolean blackWins = doesColorWin(board, BLACK);
        if (redWins && blackWins) {
            return BOTH_WIN;
        }
        if (redWins) {
            return RED_WIN;
        }
        if (blackWins) {
            return BLACK_WIN;
        }
        return NO_WINNER;
    }

    private static boolean doesColorWin(int[][] board, int color) {
        return doesColorWinVertical(board, color) ||
                doesColorWinHorizontal(board, color) ||
                doesColorWinDiagonalUp(board, color) ||
                doesColorWinDiagonalDown(board, color);
    }

    private static boolean doesColorWinVertical(int[][] board, int color) {
        for (int c = 0; c < board[0].length; c++) {
            int inARow = 0;
            for (int r = 0; r < board.length; r++) {
                if (board[r][c] == color) {
                    inARow += 1;
                } else {
                    inARow = 0;
                }
                if (inARow == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean doesColorWinHorizontal(int[][] board, int color) {
        for (int r = 0; r < board.length; r++) {
            int inARow = 0;
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == color) {
                    inARow += 1;
                } else {
                    inARow = 0;
                }
                if (inARow == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean doesColorWinDiagonalUp(int[][] board, int color) {
        for (int r = 3; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] == color &&
                        board[r - 1][c + 1] == color &&
                        board[r - 2][c + 2] == color &&
                        board[r - 3][c + 3] == color) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean doesColorWinDiagonalDown(int[][] board, int color) {
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] == color &&
                        board[r + 1][c + 1] == color &&
                        board[r + 2][c + 2] == color &&
                        board[r + 3][c + 3] == color) {
                    return true;
                }
            }
        }
        return false;
    }

}
