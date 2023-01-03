package u5pp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class Connect4Test {

	private final int R = Connect4.RED;
	private final int B = Connect4.BLACK;
	private final int E = Connect4.EMPTY;

	private int[][] BOARD_EMPTY = new int[6][7];

	private int[][] BOARD_FULL_RED = {
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
			{ R, R, R, R, R, R, R },
	};

	private int[][] BOARD_FULL_BLACK = {
			{ B, B, B, B, B, B, B },
			{ B, B, B, B, B, B, B },
			{ B, B, B, B, B, B, B },
			{ B, B, B, B, B, B, B },
			{ B, B, B, B, B, B, B },
			{ B, B, B, B, B, B, B },
	};

	private int[][] BOARD_FULL_CHECKERED = {
			{ B, R, B, R, B, R, B },
			{ R, B, R, B, R, B, R },
			{ B, R, B, R, B, R, B },
			{ R, B, R, B, R, B, R },
			{ B, R, B, R, B, R, B },
			{ R, B, R, B, R, B, R },
	};

	@Test
	public void isFull_fullBoard_returnsTrue() {
		assertTrue(Connect4.isFull(BOARD_FULL_CHECKERED));
		assertTrue(Connect4.isFull(BOARD_FULL_BLACK));
		assertTrue(Connect4.isFull(BOARD_FULL_RED));
	}

	@Test
	public void isFull_emptyBoard_returnsFalse() {
		assertFalse(Connect4.isFull(BOARD_EMPTY));
	}

	@Test
	public void isFull_almostFullBoard_returnsFalse() {
		int[][] board1 = copyBoard(BOARD_FULL_BLACK);
		board1[0][0] = E;
		int[][] board2 = copyBoard(BOARD_FULL_RED);
		board2[5][6] = E;

		assertFalse(Connect4.isFull(board1));
		assertFalse(Connect4.isFull(board2));
	}

	@Test
	public void isBoardValid_fullBoard_returnsTrue() {
		assertTrue(Connect4.isBoardValid(BOARD_FULL_BLACK));
		assertTrue(Connect4.isBoardValid(BOARD_FULL_RED));
		assertTrue(Connect4.isBoardValid(BOARD_FULL_CHECKERED));
	}

	@Test
	public void isBoardValid_emptyBoard_returnsTrue() {
		assertTrue(Connect4.isBoardValid(BOARD_EMPTY));
	}

	@Test
	public void isBoardValid_stairsBoard_returnsTrue() {
		int[][] boardStairs = {
				{ R, E, E, E, E, E, E },
				{ R, R, E, E, E, E, E },
				{ R, R, R, E, E, E, E },
				{ R, R, R, R, E, E, E },
				{ R, R, R, R, R, E, E },
				{ R, R, R, R, R, R, E },
		};

		assertTrue(Connect4.isBoardValid(boardStairs));
	}

	@Test
	public void isBoardValid_floatingPieceBoard_returnsFalse() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, E },
		};

		assertFalse(Connect4.isBoardValid(board1));
		assertFalse(Connect4.isBoardValid(board2));
		assertFalse(Connect4.isBoardValid(board3));
	}

	@Test
	public void isBoardValid_nearlyFullBoard_returnsFalse() {
		int[][] board1 = {
				{ R, R, R, R, R, R, R },
				{ E, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
		};

		int[][] board2 = {
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, E },
		};

		int[][] board3 = {
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, E, R, R, R },
				{ R, R, R, R, R, R, R },
				{ R, R, R, R, R, R, R },
		};

		assertFalse(Connect4.isBoardValid(board1));
		assertFalse(Connect4.isBoardValid(board2));
		assertFalse(Connect4.isBoardValid(board3));
	}

	@Test
	public void getWinner_horizontalRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, R, R, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, R, R, R, R, R, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, R, R, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_verticalRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ R, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_diagonalUpRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, E, E, E, E },
				{ R, R, E, E, E, E, E },
				{ E, E, R, E, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, R, E, E, E, E, E },
				{ E, E, R, R, E, E, E },
				{ E, E, E, R, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, R, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		int[][] board4 = {
				{ E, E, E, R, E, E, E },
				{ E, E, E, E, R, E, E },
				{ E, E, E, R, E, R, R },
				{ E, E, E, E, E, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_diagonalDownRedWin_returnsRedWin() {
		int[][] board1 = {
				{ R, E, E, R, E, E, E },
				{ R, E, R, E, E, E, E },
				{ E, R, R, E, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
				{ E, E, R, E, E, E, E },
				{ E, R, E, R, E, E, E },
				{ R, E, E, R, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, E, R, R },
				{ E, E, E, E, R, E, E },
				{ E, E, E, R, E, E, R },
		};

		int[][] board4 = {
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, R, R, E },
				{ E, E, E, R, R, E, R },
				{ E, E, E, R, E, E, R },
				{ E, E, E, E, E, R, E },
				{ E, E, E, E, E, E, R },
		};

		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.RED_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_horizontalBlackWin_returnsBlackWin() {
		int[][] board1 = {
				{ B, B, B, B, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, B, B, B, B, B, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ B, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, B, B, B },
		};

		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_verticalBlackWin_returnsBlackWin() {
		int[][] board1 = {
				{ B, E, E, E, E, E, E },
				{ B, E, E, E, E, E, E },
				{ B, E, E, E, E, E, E },
				{ B, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, E, E, E },
				{ E, E, E, B, E, E, E },
				{ E, E, E, B, E, E, E },
				{ E, E, E, B, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, B },
				{ E, E, E, E, E, E, B },
				{ E, E, E, E, E, E, B },
				{ E, E, E, E, E, E, B },
		};

		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board3));
	}

	@Test
	public void getWinner_diagonalUpBlackWin_returnsBlackWin() {
		int[][] board1 = {
				{ B, E, E, E, E, E, E },
				{ B, B, E, E, E, E, E },
				{ E, E, B, E, E, E, E },
				{ B, E, E, B, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, E, E, E },
				{ B, E, E, B, E, E, E },
				{ E, B, E, E, E, E, E },
				{ E, E, B, B, E, E, E },
				{ E, E, E, B, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, E, E, B },
				{ E, E, E, E, B, E, B },
				{ E, E, E, E, E, B, E },
				{ E, E, E, E, E, E, B },
		};

		int[][] board4 = {
				{ E, E, E, B, E, E, E },
				{ E, E, E, E, B, E, E },
				{ E, E, E, B, E, B, B },
				{ E, E, E, E, E, E, B },
				{ E, E, E, E, E, B, E },
				{ E, E, E, E, E, E, B },
		};

		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_diagonalDownBlackWin_returnsBlackWin() {
		int[][] board1 = {
				{ B, E, E, B, E, E, E },
				{ B, E, B, E, E, E, E },
				{ E, B, B, E, E, E, E },
				{ B, E, E, B, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, E, E, E },
				{ B, E, E, B, E, E, E },
				{ E, E, B, E, E, E, E },
				{ E, B, E, B, E, E, E },
				{ B, E, E, B, E, E, E },
		};

		int[][] board3 = {
				{ E, E, E, E, E, E, E },
				{ E, E, E, E, E, E, E },
				{ E, E, E, B, E, E, B },
				{ E, E, E, E, E, B, B },
				{ E, E, E, E, B, E, E },
				{ E, E, E, B, E, E, B },
		};

		int[][] board4 = {
				{ E, E, E, B, E, E, B },
				{ E, E, E, E, B, B, E },
				{ E, E, E, B, B, E, B },
				{ E, E, E, B, E, E, B },
				{ E, E, E, E, E, B, E },
				{ E, E, E, E, E, E, B },
		};

		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.BLACK_WIN, Connect4.getWinner(board4));
	}

	@Test
	public void getWinner_tie_returnsBothWin() {
		int[][] board1 = {
				{ B, E, E, B, E, E, E },
				{ B, E, B, E, E, E, E },
				{ E, B, B, E, E, E, E },
				{ B, E, E, B, E, E, E },
				{ R, R, R, R, R, R, R },
				{ E, E, E, E, E, E, E },
		};

		int[][] board2 = {
				{ E, E, E, E, E, E, R },
				{ E, E, E, B, E, R, E },
				{ B, E, E, E, R, E, E },
				{ E, E, B, R, E, E, E },
				{ B, B, B, B, E, E, E },
				{ B, E, E, B, E, E, E },
		};

		int[][] board3 = {
				{ R, E, E, E, E, E, E },
				{ E, R, E, E, E, E, E },
				{ E, E, R, B, E, E, B },
				{ E, E, E, R, E, B, B },
				{ E, E, E, E, B, E, E },
				{ E, E, E, B, E, E, B },
		};

		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board1));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board2));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(board3));
		assertEquals(Connect4.BOTH_WIN, Connect4.getWinner(BOARD_FULL_CHECKERED));
	}

	@Test
	public void getWinner_noWinnerBoard_returnsNoWinner() {
		int[][] board1 = {
			{ R, R, R, E, B, B, B},
			{ B, B, B, E, R, R, R},
			{ R, R, R, E, B, B, B},
			{ B, B, B, E, R, R, R},
			{ R, R, R, E, B, B, B},
			{ B, B, B, E, R, R, R},
		};
		assertEquals(Connect4.NO_WINNER, Connect4.getWinner(BOARD_EMPTY));
		assertEquals(Connect4.NO_WINNER, Connect4.getWinner(board1));
	}

	private int[][] copyBoard(int[][] board) {
		int[][] output = new int[board.length][board[0].length];
		for (int r = E; r < board.length; r++) {
			for (int c = E; c < board[r].length; c++) {
				output[r][c] = board[r][c];
			}
		}
		return output;
	}

}
