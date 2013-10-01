import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestingHarness {

	public static void main(String[] args) {
		ArrayList<Board> boards = null;
		try {
			boards = inputBoards();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<SudokuSolver> solvers = new ArrayList<SudokuSolver>();

		solvers.add(new BasicBacktracking());
		solvers.add(new ForwardChecking());
		solvers.add(new ArcConsistency());
		solvers.add(new AllDiff());
		

		for (SudokuSolver s : solvers) {

			for (Board b : boards) {
				s.solveSudoku(b);
			}
			System.out.println("----------------");
		}

	}

	public static ArrayList<Board> inputBoards() throws IOException {
		ArrayList<Board> ans = new ArrayList<Board>();
		FileReader in = new FileReader("testcases.txt");
		BufferedReader br = new BufferedReader(in);

		for (int r = 0; r < 10; r++) {
			byte[][] b = new byte[9][9];
			// Consume the text
			br.readLine();

			for (int i = 0; i < 9; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < 9; j++) {
					b[i][j] = (byte) (line[j] - '0');
				}
			}

			Board board = new Board(b);
			ans.add(board);

		}

		br.close();

		return ans;

	}

}
