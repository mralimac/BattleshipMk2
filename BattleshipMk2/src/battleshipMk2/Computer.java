package battleshipMk2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Computer 
{
	//Attribute Section
	private Board computerBoard;
	//End Attribute
	
	//Constructor Section
	public Computer(Board board)
	{
		this.computerBoard = board;
	}
	//End Constructor
	
	//Method Section
	public void placeShips()
	{
		computerBoard.addAIShipToBoard("AI Minesweeper", 1, 3, 4, 0);
		computerBoard.addAIShipToBoard("AI Corvette", 2, 2, 2, 5);
		computerBoard.addAIShipToBoard("AI Submarine", 3, 1, 7, 6);
		computerBoard.addAIShipToBoard("AI Battleship", 4, 2, 0, 7);
		computerBoard.addAIShipToBoard("AI Aircraft Carrier", 5, 2, 2, 4);
	}
	
	public void fire()
	{
		//Math.random()*10;
	}
	
	public void givenList_shouldReturnARandomElement() {
	    List<Integer> givenList = Arrays.asList(1, 2, 3);
	    Random rand = new Random();
	    int randomElement = givenList.get(rand.nextInt(givenList.size()));
	}
	//End Method
}
