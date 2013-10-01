import java.util.Set;

/*
 * Part (b) of problem 4. 
 * 
 * At each move, roots out any states that are 
 * required to be fixed. If any have no possibilities,
 * clears out. 
 */
public class ForwardChecking extends SudokuSolver {

	@Override
	public boolean constraintCheck(Board b, int cur) {

		int row = cur / 9;
		int col = cur % 9;
		int sqrow = (row / 3) * 3;
		int sqcol = (col / 3) * 3;

		// Check all cols
		for (int i = 0; i < 9; i++) {
			int idx = 9 * i + col;
			if (b.get(idx) != 0)
				continue;

			Set<Integer> poss = getPossibleValues(b, idx);
			if (poss.size() == 1) {
				b.set(idx, poss.iterator().next());
			}
			if (poss.size() == 0) {
				return false;
			}
		}

		// Check all rows
		for (int j = 0; j < 9; j++) {
			int idx = 9 * row + j;
			if (b.get(idx) != 0)
				continue;

			Set<Integer> poss = getPossibleValues(b, idx);
			if (poss.size() == 1)
				b.set(idx, poss.iterator().next());
			if (poss.size() == 0)
				return false;
		}

		// Check in square
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b.board[sqrow + i][sqcol + j] != 0)
					continue;
				int idx = 9 * (i + sqrow) + j + sqcol;

				Set<Integer> poss = getPossibleValues(b, idx);
				if (poss.size() == 1)
					b.set(idx, poss.iterator().next());
				if (poss.size() == 0)
					return false;
			}
		}

		return true;
	}

}
