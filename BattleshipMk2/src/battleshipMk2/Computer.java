package battleshipMk2;

import java.util.Arrays;

import java.util.List;
import java.util.Random;

public class Computer
{
	//Attribute Section
	private Board aiBoard;
	//End Attribute
	
	//Constructor Section
	public Computer()
	{
		this.aiBoard = new Board();
	}
	//End Constructor
	
	//Method Section
	public void placeShips()
	{
		aiBoard.addAIShipToBoard("AI Minesweeper", 1);
		aiBoard.addAIShipToBoard("AI Corvette", 2);
		//addAIShipToBoard("AI Submarine", 3, 1, 7, 6);
		//addAIShipToBoard("AI Battleship", 4, 2, 0, 7);
		//addAIShipToBoard("AI Aircraft Carrier", 5, 2, 2, 4);
	}
	
	public void fire()
	{
		//Math.random()*10;
	}
	
	public Board getAIBoard()
	{
		return this.aiBoard;
	}
	
	public void getAIBoardPrintOut()
	{
		this.aiBoard.printOutGamePlayBoard();
	}
	
	public void givenList_shouldReturnARandomElement() {
	    List<Integer> givenList = Arrays.asList(1, 2, 3);
	    Random rand = new Random();
	    int randomElement = givenList.get(rand.nextInt(givenList.size()));
	}
	//End Method
}
