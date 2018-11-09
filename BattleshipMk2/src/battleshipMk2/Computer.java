package battleshipMk2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

import java.util.List;
import java.util.Random;

public class Computer
{
	//Attribute Section
	private Board aiBoard;
	private int comScore;
	//End Attribute
	
	//Constructor Section
	public Computer(Board aiBoard)
	{
		this.aiBoard = aiBoard;
		placeShips();
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
		//So the AI will pick a tile with H on it as a starting point
		//Check each tile around it for a M, in which case it will ignore that direction
		//If two H tiles are in a row, it will want to target the next tile in line
		//if no H tiles are present, then pick tile at random
		//Make the AI not pick a tile that is already fired at
		
		
		int randomXCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getLengthOfBoard());
		int randomYCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getHeightOfBoard());
		aiBoard.getTile(randomXCoord, randomYCoord);
	}
	
	public boolean plotFiringSolution()
	{
		//Use this function to write the logic of targeting each tile and then relay back to the fire function on if it should fire or not
		return false;
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
