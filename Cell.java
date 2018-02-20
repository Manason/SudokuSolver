import java.util.ArrayList;
import java.util.Arrays;


public class Cell{
	public ArrayList<Integer> possibleValues;
	public int x, y;	
	
	//constructs a blank cell
	public Cell(){
		possibleValues = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}
	
	//constructs a cell with a given number
	public Cell(int number){
		possibleValues = new ArrayList<Integer>();
		possibleValues.add(number);
	}

	//constructs a cell as a copy of another cell
	public Cell(Cell c){
		possibleValues = new ArrayList<Integer>(c.possibleValues);
		x = c.x;
		y = c.y;
	}
	
	//returns whether cell has been solved
	public boolean solved(){
		if(possibleValues.size() == 0 || possibleValues.size() == 1)
			return true;
		return false;
	}

	//returns the value of the cell if found, otherwise returns 0
	public int value(){
		if(solved())
			return possibleValues.get(0);
		else
			return 0;
	}
	
	//sets the cell to a passed in value
	public void setValue(int value){
		possibleValues.clear();
		possibleValues.add(value);
	}
}
