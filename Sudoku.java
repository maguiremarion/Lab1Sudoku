import java.util.*;

public class Sudoku {

	private static int[][] sudoku_board = new int[9][9];	//sudoku board being solved
	private int[][] board;

	private Sudoku(int[][] board) {
		this.board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	private boolean isInRow(int row, int number) {	//method checks if a possible number is already in a row
		for (int i = 0; i < 9; i++)
			if (board[row][i] == number)
				return true;
		return false;
	}
	
	private boolean isInCol(int col, int number) {	//method checks if a possible number is already in column
		for (int i = 0; i < 9; i++)
			if (board[i][col] == number)
				return true;
		return false;
	}
	
	private boolean isInSquare(int row, int col, int number) {	//method checks if a possible number is already in 3x3 subsquare
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;
		return false;
	}
	
	private boolean isOk(int row, int col, int number) {    //method verifies row, column, and subsquare is ok using the other methods
		return !isInRow(row, number) && !isInCol(col, number) && !isInSquare(row, col, number);
	}
	
	private boolean solve() {					//method implementing recursive backtracking algorithm to solve board
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {		//searches for an empty cell
					for (int number = 1; number <= 9; number++) {	// try possible numbers 1-9
						if (isOk(row, col, number)) {	//if valids spot, assign number to that board spot
							board[row][col] = number;

							if (solve()) { 	//recursive backtracking; calls solve() within solve()
									return true;
							} else { 		//if not a valid solution, keep cell empty and continue
								board[row][col] = 0;
							}
						}
					}

					return false; //we return false
				}
			}
         }

         return true; //sudoku board is successfully solved
	}
	
	private void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + board[i][j]);
			}
		
			System.out.println();
		}
		
		System.out.println();
	}
	
	private static void enterBoard() {
    	Scanner input = new Scanner(System.in);
		for (int i=0; i<9;i++){   
			System.out.print("Enter 9 Values: ");
            for (int j=0; j<9;j++){
            	sudoku_board[i][j]=input.nextInt();
            }
         }
		input.close();
	}
	
	public static void main(String[] args) {
		enterBoard();
		Sudoku sudoku = new Sudoku(sudoku_board);
		System.out.println("Given sudoku board:");
		sudoku.printBoard();
		
		if (sudoku.solve()) {
			System.out.println("Solved sudoku board:");
			sudoku.printBoard();
		} else {
			System.out.println("Sudoku board is unsolvable :(");
		}
	}
}
