import java.util.HashSet;
import java.util.Set;

/*
 * Uses the All Diff constraint checker. 
 */
public class AllDiff extends SudokuSolver {

	@Override
	public boolean constraintCheck(Board b, int cur) {

		int row = cur / 9;
		int col = cur % 9;
		int sqrow = (row / 3) * 3;
		int sqcol = (col / 3) * 3;

		Set<Integer> choices = new HashSet<Integer>();
		int unset = 0;

		// Check all cols
		for (int i = 0; i < 9; i++) {
			int idx = 9 * i + col;
			if (b.get(idx) != 0)
				continue;

			unset++;

			Set<Integer> poss = getPossibleValues(b, idx);
			choices.addAll(poss);
		}

		if (choices.size() < unset)
			return false;

		choices = new HashSet<Integer>();
		unset = 0;

		// Check all rows
		for (int j = 0; j < 9; j++) {
			int idx = 9 * row + j;
			if (b.get(idx) != 0)
				continue;

			unset++;

			Set<Integer> poss = getPossibleValues(b, idx);
			choices.addAll(poss);
		}

		if (choices.size() < unset)
			return false;

		choices = new HashSet<Integer>();
		unset = 0;

		// Check in square
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b.board[sqrow + i][sqcol + j] != 0)
					continue;
				int idx = 9 * (i + sqrow) + j + sqcol;

				unset++;

				Set<Integer> poss = getPossibleValues(b, idx);
				choices.addAll(poss);
			}
		}

		if (choices.size() < unset)
			return false;

		return true;
	}

}
