/* SudokuSolver
 * Description: Solves a Sudoku puzzle
 */
package models;
import java.util.*;


public class SudokuSolver{
	public Cell[][] board;
	public SudokuSolver(int[][] testBoard){
		board = new Cell[9][9];
		setupBoard(testBoard, board);
	}	
	
	//populates thisBoard from testBoard representation where 0 represents a blank cell
	public void setupBoard(int[][] testBoard, Cell[][] thisBoard){
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				Cell c;
		
				if(testBoard[i][j] == 0)
					c = new Cell();
				else
					c = new Cell(testBoard[i][j]);
				
				c.x = j;
				c.y = i;
				thisBoard[i][j] = c;
			}
		}
	}
	
	//prints the board
	public void printBoard(Cell[][] thisBoard){
		//copyBoard(thisBoard, thisBoard);
		for(int i = 0; i < 9; i++){
			if(i % 3 == 0)
				System.out.print("------------\n");
			for(int j = 0; j < 9; j++){
				if(j % 3 == 0)
					System.out.print("|");
				System.out.print(thisBoard[i][j].value());
			}
			System.out.print("|");
			System.out.println("");
		}
		System.out.println("");
	}

	//solves passed in board and returns the solved board
	public Cell[][] solveBoard(Cell[][] thisBoard){
		Cell[][] deepBoard = new Cell[9][9];
		copyBoard(thisBoard, deepBoard); //need to deep copy the board at the top of the method so that the recursion works
		
		//use deduction to update all cells until forced to guess
		while(updateBoard(deepBoard) > 0){
			if(checkValid(deepBoard) == false){
				return deepBoard;
			}
		
		}
		
		
		if(isSolved(deepBoard))
			return deepBoard;
		
		//pick cell with the fewest possible values for guessing
		Cell c = getLowestPossibleCell(deepBoard);		
		ArrayList<Integer> possibleValues = new ArrayList<Integer>(c.possibleValues);
		
		//recursively guess each possible value
		for(int value : possibleValues){
			Cell[][] backupBoard = new Cell[9][9];
			copyBoard(deepBoard, backupBoard);
			deepBoard[c.y][c.x].setValue(value);
			
			deepBoard = solveBoard(deepBoard);
			if(!isSolved(deepBoard))
				copyBoard(backupBoard, deepBoard);
			else if(isSolved(deepBoard))
				return deepBoard;	
		}
		return deepBoard;	
	}

	//deep copies the src board to the dest board
	public void copyBoard(Cell[][] src, Cell[][] dest){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				dest[i][j] = new Cell(src[i][j]);
			}
		}
	}

	//returns cell with the lowest amount of possible values. Exits program if there are no blank cells
	public Cell getLowestPossibleCell(Cell[][] thisBoard){
		Cell lowest = new Cell();
		lowest.x = -1;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(thisBoard[i][j].possibleValues.size() <= lowest.possibleValues.size() && thisBoard[i][j].possibleValues.size() > 1)
					lowest = thisBoard[i][j];	
			}
		}
		if(lowest.x == -1){
			System.out.println("cannot get lowestPossibleCell() as there are no blank cells");
			System.exit(1);
		}
			
		return lowest;
	}

	//checks if board is solved, returns true if so, false otherwise
	public boolean isSolved(Cell[][] thisBoard){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(thisBoard[i][j].solved() == false)
					return false;
			}
		}
		return true;
	}

	//checks if the board is valid, meaning there are no duplicate neighbors. Ignores blank cells
	public boolean checkValid(Cell[][] thisBoard){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(checkValidCell(thisBoard, thisBoard[i][j]) == false){
					return false;
				}
				
			}
		}
		return true;
	}

	//checks whether the given cell is valid, ignores blank cells
	public boolean checkValidCell(Cell[][] thisBoard, Cell c){
		
		//check that cell still has a possible value
		if(c.possibleValues.size() == 0)
			return false;
		
		//check rows and cols neighbors
		for(int i = 0; i < 9; i++){
			if(thisBoard[c.y][i].possibleValues.size() == 0 || (thisBoard[c.y][i].value() != 0 && thisBoard[c.y][i].value() == c.value() && !thisBoard[c.y][i].equals(c)))
				return false;
			
			if(thisBoard[i][c.x].possibleValues.size() == 0 || (thisBoard[i][c.x].value() != 0 && thisBoard[i][c.x].value() == c.value() && !thisBoard[i][c.x].equals(c)))
				return false;
		}
		
		//check neighbors within the cells box
		int offSetX = c.x / 3 * 3;
		int offSetY = c.y / 3 * 3;

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(thisBoard[i + offSetY][offSetX + j].value() != 0 && thisBoard[i + offSetY][j + offSetX].value() == c.value() && !thisBoard[i + offSetY][j + offSetX].equals(c))
					return false;
			}
		}
		return true;
	}

	//updates the board using deduction, returns the number of changes (possibleValues that were able to be removed from cells)
	public int updateBoard(Cell[][] thisBoard){
		int changed = 0;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				changed += updateValues(thisBoard, thisBoard[i][j]);
				if(checkValidCell(thisBoard, thisBoard[i][j]) == false){
					return changed;
				}
			}
		}
		return changed;
	}

	//upates a specific cell using deduction by looking at all neighbors, returns number of changes
	public int updateValues(Cell[][] thisBoard, Cell c){
		int changed = 0;
		//check rows and cols
		for(int i = 0; i < 9; i++){
			if(!thisBoard[c.y][i].equals(c))
				if(c.possibleValues.remove(Integer.valueOf(thisBoard[c.y][i].value()))) //rows
					changed++;
			if(!thisBoard[i][c.x].equals(c))
				if(c.possibleValues.remove(Integer.valueOf(thisBoard[i][c.x].value()))) //cols
					changed++;
		}
		
		//set offsets to determine which 9x9 box c is in
		int offSetX = c.x / 3 * 3;
		int offSetY = c.y / 3 * 3;
		
		//use offsets to check adjacent cells within same box as c
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				if(!thisBoard[offSetY + i][offSetX + j].equals(c))
					if(c.possibleValues.remove(Integer.valueOf(thisBoard[offSetY + i][offSetX + j].value())))
						changed++;
			}
		}
		int found = findUniqueValues(thisBoard, c);
		if(found != 0){
			c.setValue(found);
			changed++;
		}
		return changed;
	}
	
	//checks to see if a possibility of Cell c is not shared by any neighbors, returns the value if found, otherwise returns 0
	public int findUniqueValues(Cell[][] thisBoard, Cell c){
		int offSetX = c.x / 3 * 3;
		int offSetY = c.y / 3 * 3;
		boolean isUnique = true;

		//for every possible value c has
		for(int value : c.possibleValues){

			//check rows and cols for uniqueness
			for(int i = 0; i < 9; i++){
				if(thisBoard[c.y][i].possibleValues.contains(value) || thisBoard[i][c.x].possibleValues.contains(value)){
					isUnique = false;
					break;
				}
			}
			
			if(isUnique == true){
				//check box for uniqueness
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){
						if(thisBoard[offSetY + i][offSetX + j].possibleValues.contains(value) && !thisBoard[offSetY + i][offSetX + j].equals(c)){
							isUnique = false;
							return 0;
						}
					}
				}
				return value;	
			}
			else
				return 0;			
		}
		return 0;		
	}
}
