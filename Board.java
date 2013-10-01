import java.util.HashSet;
import java.util.Set;

/*
 * Sudoku board class for a 9x9 sudoku
 */
class Board {
	byte[][] board;
	int N = 9;

	public Board() {
		board = new byte[N][N];
	}

	public Board(byte[][] preset) {
		board = preset;
	}

	public void set(int idx, int val) {
		int row = idx / N;
		int col = idx % N;
		board[row][col] = (byte) val;
	}

	public int get(int idx) {
		int row = idx / N;
		int col = idx % N;
		return board[row][col];
	}

	public Set<Integer> getPossibleCol(int col) {
		boolean[] used = new boolean[10];
		for (int i = 0; i < N; i++)
			used[board[i][col]] = true;

		Set<Integer> ans = new HashSet<Integer>();

		for (int i = 1; i < 10; i++) {
			if (!used[i])
				ans.add(i);
		}

		return ans;
	}

	public Set<Integer> getPossibleSq(int s) {
		// Check all squares
		int sqrow = (s / 3) * 3;
		int sqcol = (s % 3) * 3;
		boolean[] seen = new boolean[N + 1];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int cur = board[sqrow + i][sqcol + j];
				seen[cur] = true;
			}
		}

		Set<Integer> ans = new HashSet<Integer>();

		for (int i = 1; i < 10; i++) {
			if (!seen[i])
				ans.add(i);
		}

		return ans;
	}

	public Set<Integer> getPossibleRow(int row) {
		boolean[] used = new boolean[10];
		for (int j = 0; j < N; j++) {
			used[board[row][j]] = true;
		}

		Set<Integer> ans = new HashSet<Integer>();

		for (int i = 1; i < 10; i++) {
			if (!used[i])
				ans.add(i);
		}

		return ans;
	}

	public boolean invalidState() {
		// Check all rows
		for (int i = 0; i < N; i++) {
			boolean[] seen = new boolean[N + 1];
			for (int j = 0; j < N; j++) {
				int cur = board[i][j];
				if (cur != 0 && seen[cur])
					return true;

				seen[cur] = true;
			}

		}

		// Check all columns
		for (int j = 0; j < N; j++) {
			boolean[] seen = new boolean[N + 1];
			for (int i = 0; i < N; i++) {
				int cur = board[i][j];
				if (cur != 0 && seen[cur])
					return true;

				seen[cur] = true;
			}
		}

		// Check all squares
		for (int s = 0; s < N; s++) {
			int sqrow = (s / 3) * 3;
			int sqcol = (s % 3) * 3;
			boolean[] seen = new boolean[N + 1];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					int cur = board[sqrow + i][sqcol + j];
					if (cur != 0 && seen[cur]) {
						return true;
					}

					seen[cur] = true;
				}
			}

		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < N * N; i++) {
			if (i % N == 0)
				b.append("\n");

			b.append(get(i) + " ");
		}

		return b.toString();
	}

	/* Creates a deep clone */
	public Board clone() {

		byte[][] clone = new byte[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				clone[i][j] = board[i][j];
			}
		}

		return new Board(clone);
	}

}
