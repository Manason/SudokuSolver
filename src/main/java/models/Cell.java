package models;

import java.util.ArrayList;
import java.util.Arrays;


public class Cell{
	public ArrayList<Integer> possibleValues;
	public int x, y;
	public int value;
	
	//constructs a blank cell
	public Cell(){
		possibleValues = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		value = 0;
	}
	
	//constructs a cell with a given number
	public Cell(int number){
		possibleValues = new ArrayList<Integer>();
		possibleValues.add(number);
		value = 0;
	}

	//constructs a cell as a copy of another cell
	public Cell(Cell c){
		possibleValues = new ArrayList<Integer>(c.possibleValues);
		x = c.x;
		y = c.y;
		value = c.value;
	}
	
	//returns whether cell has been solved
	public boolean solved(){
		if(possibleValues.size() == 0 || possibleValues.size() == 1)
			return true;
		return false;
	}

	//returns the value of the cell if found, otherwise returns 0
	public int value(){
		if(solved()) {
			value = possibleValues.get(0);
			return value;
		}
		else
			return 0;
	}
	
	//sets the cell to a passed in value
	public void setValue(int value){
		possibleValues.clear();
		possibleValues.add(value);
	}
}
