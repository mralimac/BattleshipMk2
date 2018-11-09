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
		aiBoard.addAIShipToBoard("AI Submarine", 3);
		aiBoard.addAIShipToBoard("AI Battleship", 4);
		aiBoard.addAIShipToBoard("AI Aircraft Carrier", 5);
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
