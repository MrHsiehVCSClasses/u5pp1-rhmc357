package u5pp1;

public class Connect4 {

    //declares variables
    public static final int RED_WIN = 0;
    public static final int BLACK_WIN = 1;
    public static final int NO_WINNER = 2;
    public static final int BOTH_WIN = 3;

    public static final int RED = 1;
    public static final int BLACK = -1;
    public static final int EMPTY = 0;

    //returns true if all the board spaces have a piece in them
    public static boolean isFull(int[][] board){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board [row][col] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    //returns true if the board is a valid configuration. 
    //A board is valid if there are no floating pieces
    public static boolean isBoardValid(int[][] board){
        for (int col = 0; col < board[0].length; col++){
            for (int row = 0; row < board.length; row++){
                boolean checkUnder = false;
                if (row + 1 < 6){
                    checkUnder = true;
                }
                if ((board [row][col] == RED || board [row][col] == BLACK) && checkUnder && board [row + 1][col] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    //Declares the winner (Red, Black, tie, Scratch) gives the numerical value for each
    public static int getWinner(int[][] board){
        boolean red = false;
        boolean black = false;
        // Checks for horizontal winner
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                boolean withinRange = true;
                if (col + 3 >= 7){
                    withinRange = false;
                }
                if (withinRange && board [row][col] == RED && board [row][col + 1] == RED && board [row][col + 2] == RED && board [row][col + 3] == RED){
                    red = true;
                }
                else if (withinRange && board [row][col] == BLACK && board [row][col + 1] == BLACK && board [row][col + 2] == BLACK && board [row][col + 3] == BLACK){
                    black = true;
                }
            }
        }
        // Checks for verticle winner 
        for (int col = 0; col < board[0].length; col++){
            for (int row = 0; row < board.length; row++){
                boolean withinRange = true;
                if (row + 3 >= 7){
                    withinRange = false;
                }
                if (withinRange && board [row][col] == RED && board [row +1][col] == RED && board [row + 2][col] == RED){
                    red = true;
                }
                else if (withinRange && board [row][col] == BLACK && board [row + 1][col] == BLACK && board [row + 2][col] == BLACK){
                    black = true;
                }
            }
        }
        // Checks for diagonal winners
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                boolean withinRange = true;
                if (col + 3 >= 7 || row + 3 >= 6 ){
                    withinRange = false;
                } 
                if (withinRange && board [row][col] == RED && board [row + 1][col + 1] == RED && board [row + 2][col + 2] == RED && board [row + 3][col + 3] == RED){
                    red = true;
                }
                else if (withinRange && board [row][col] == BLACK && board [row + 1][col + 1] == BLACK && board [row + 2][col + 2] == BLACK && board [row + 3][col + 3] == BLACK){
                    black = true;
                }
                boolean wr = true;
                if (row - 3 < 0 || col + 3 > 6){
                    wr = false;
                }
                if (wr && board [row][col] == RED && board [row - 1][col + 1] == RED && board [row - 2][col + 2] == RED && board [row - 3][col + 3] == RED){
                    red = true;
                }
                else if (wr && board [row][col] == BLACK && board [row - 1][col + 1] == BLACK && board [row - 2][col + 2] == BLACK && board [row - 3][col + 3] == BLACK){
                    black = true;
                }
            }
        }
        return checkBoth(red, black);
    }

    //helper method that decides who wins overall or if it is a tie
    private static int checkBoth(boolean red, boolean black){
        if (red == true && black == true){
            return BOTH_WIN;
        }
        else if (red == true){
            return RED_WIN;
        }
        else if (black == true){
            return BLACK_WIN;
        }
        else {
            return NO_WINNER;
        }
    }
}