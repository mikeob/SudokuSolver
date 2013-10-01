import java.util.HashSet;
import java.util.Set;



/*
 * Very naive backtracking solution to solving Sudoku
 */
public class BasicBacktracking extends SudokuSolver{

	@Override
	public Set<Integer> getPossibleValues(Board b, int cur) {
		Set<Integer> ans = new HashSet<Integer>();
		for (int i = 1; i <= 9; i++)
			ans.add(i);
		return ans;
	}

	@Override
	public boolean constraintCheck(Board b, int cur) {
		return true;
	}

}
