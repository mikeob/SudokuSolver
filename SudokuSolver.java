import java.util.HashSet;
import java.util.Set;

public abstract class SudokuSolver {

	static final int UNSET = 0;
	int backtrackCalls;
	long timeSolving;
	long timeThinking;
	long startTime;
	long endTime;

	/* Solves a sudoku */
	public void solveSudoku(Board b) {
		backtrackCalls = 0;
		timeSolving = System.currentTimeMillis();
		timeThinking = 0;

		if (!backtrack(b, 0))
			System.out.println("No solution possible!");

		timeSolving = System.currentTimeMillis() - timeSolving - timeThinking;
		System.out.printf("Took %.3f seconds solving\n", timeSolving / 1000.0);
		System.out
				.printf("Took %.3f seconds thinking\n", timeThinking / 1000.0);
		System.out.println(backtrackCalls + " calls of backtrack()");

	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	public long stopTimer() {
		endTime = System.currentTimeMillis();
		return (endTime - startTime);
	}

	public boolean backtrack(Board b, int cur) {
		backtrackCalls++;

		// Get next blank spot
		while (cur < 81 && b.get(cur) != 0)
			cur++;

		if (b.invalidState())
			return false;

		if (cur == 81) {
			// System.out.print("Solution:");
			// System.out.println(b.toString());
			return true;
		}

		Set<Integer> possibleValues = getPossibleValues(b, cur);
		for (Integer i : possibleValues) {

			Board clone = b.clone();
			clone.set(cur, i);
			
			startTimer();
			boolean pass = constraintCheck(clone, cur);
			timeThinking += stopTimer();

			/* Use specified constraint check. If it passes, recurse.
			 * NOTE: Possibly alters state!! */
			if (pass) {

				if (backtrack(clone, cur))
					return true;
			}

		}

		// Failure
		return false;
	}

	public Set<Integer> getPossibleValues(Board b, int cur) {
		Set<Integer> possible = new HashSet<Integer>();
		for (int i = 1; i < 10; i++)
			possible.add(i);

		int row = cur / 9;
		int col = cur % 9;
		int square = (row / 3) * 3 + col / 3;

		possible.retainAll(b.getPossibleCol(col));
		possible.retainAll(b.getPossibleRow(row));
		possible.retainAll(b.getPossibleSq(square));

		return possible;
	}

	public abstract boolean constraintCheck(Board b, int cur);

}
