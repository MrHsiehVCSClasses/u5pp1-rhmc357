# u5pp1

In this project, you will create a couple of helper methods for the game of Connect 4. You will not be making the main gameplay loop. :)

## Connect 4 Rules

TLDR: If you don't know the rules, google it, or watch a youtube play through.

Boards are 6 tall, by 7 wide. Pieces are added from the top, and pieces fall to the lowest available spot in their column, due to gravity. Pieces may not be added to columns that are full. First person to have 4-in-a-row (vertically, horizontally, or diagonally) wins.

The specific value of each space is determined by final variables that are included in the class (`RED`/`BLACK`/`EMPTY`).

## `Connect4.java`

- `public static boolean isFull(int[][] board)` - returns true if all the board spaces have a piece in them. Red pieces are indicated by `1` and black pieces are indicated by `-1`. Empty spaces are indicated by `0`.
- `public static boolean isBoardValid(int[][] board)` - returns true if the board is a valid configuration. A board is valid if there are no floating pieces.
- `public static int getWinner(int[][] board)` - returns `RED_WIN` if only red wins, and `BLACK_WIN` if only black wins. Returns `NO_WINNER` if neither have won. Returns `BOTH_WIN` if both red and black have won. Each of these return variables are found at the top of the class.

**Note**: boards are stored as row-major 2D arrays, as is convention. This means that `board[0][4]` will access the top row, 5th column.

## Implementation Hints

Implementation Order: Start from `isFull`, then `isBoardValid`, then `getWinner`. For `getWinner`, do detection for horizontal wins first, then vertical, then the diagonals.

`isFull`: as soon as you find one empty spot, the board is NOT full.

`isBoardValid`: a board is valid if there are no empty spaces under any pieces. A column major traversal is recommended.

`getWinner`: try to do method decomposition. One possible way is to split up the logic for determining what to return, from the logic that finds 4-in-a-rows. You could also split up the logic for each type of 4 in a row. Do whatever makes the problem easier for you to wrap your mind around. Good code doesn't mean least number of lines. Good code means easy to understand.

## Extra credit

- actual gameplay (`play()` method)
  - possible helper methods: `dropPiece(int color, int column)`, `takePlayerTurn()`, `takeAiTurn()`
- AI to play against (picks move randomly)
- AI (picks winning moves)
- AI (tries to make 3 in a row if possible)
- AI (anything more complicated)

## Grading Breakdown

- Proper formatting/indentation: 2 points
- Commented all code: 2 points
- Has no public members other than those specified: 2 points
- Pass all test cases : 6 pts
Total: 12 points
