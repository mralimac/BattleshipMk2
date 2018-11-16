package battleshipMk2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Random;

public class Computer
{
	//Attribute Section
	private Board aiBoard;
	private int comScore;
	private ArrayList<Tile> tilesThatAreHit = new ArrayList<Tile>();
	private ArrayList<Tile> tilesThatAreMiss = new ArrayList<Tile>();
	private ArrayList<Tile> tilesThatAreSunk = new ArrayList<Tile>();
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
//		aiBoard.addAIShipToBoard("AI Corvette", 2);
//		aiBoard.addAIShipToBoard("AI Submarine", 3);
//		aiBoard.addAIShipToBoard("AI Battleship", 4);		
//		aiBoard.addAIShipToBoard("AI Aircraft Carrier", 5);
	}
	
	public void fire()
	{
		//So the AI will pick a tile with H on it as a starting point
		//Check each tile around it for a M, in which case it will ignore that direction
		//If two H tiles are in a row, it will want to target the next tile in line
		//if no H tiles are present, then pick tile at random
		//Make the AI not pick a tile that is already fired at
		
		
	}
	
	public void fireAtRandom()
	{
		int randomXCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getLengthOfBoard());
		int randomYCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getHeightOfBoard());
		while(!isTileSunkenOrMiss(randomXCoord, randomYCoord))
		{
			System.out.println("AI needs to reroll its selection");
			randomXCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getLengthOfBoard());
			randomYCoord = ThreadLocalRandom.current().nextInt(0, this.aiBoard.getHeightOfBoard());			
		}
		
	}
	
	
	public String fireAtTarget(int xCoord, int yCoord)
	{
		aiBoard.fireAtTile(xCoord, yCoord);
		return aiBoard.getTile(xCoord, yCoord).getContentOfTile();		
	}
	
	public void recordTileContent(int xCoord, int yCoord, String contentOfTile)
	{
		if(contentOfTile.equalsIgnoreCase("Empty"))
		{
			
		}
	}
	
	public boolean isTileSunkenOrMiss(int xCoord, int yCoord)
	{
		if(aiBoard.getTile(xCoord, yCoord).getContentOfTile().equalsIgnoreCase("Miss") || aiBoard.getTile(xCoord, yCoord).getContentOfTile().equalsIgnoreCase(""))
		{
			
		}
		
		for(int x = 0; x < tilesThatAreMiss.size(); x++)
		{
			if(xCoord == tilesThatAreMiss.get(x).getXCoord() && yCoord == tilesThatAreMiss.get(x).getYCoord()) 
			{
				//The AI has picked a miss tile
				return false;
			}
		}
		
		for(int i = 0; i < tilesThatAreSunk.size(); i++)
		{
			if(xCoord == tilesThatAreSunk.get(i).getXCoord() && yCoord == tilesThatAreSunk.get(i).getYCoord()) 
			{
				//The AI has picked a sunken tile
				return false;
			}
		}
		//Use this function to write the logic of targeting each tile and then relay back to the fire function on if it should fire or not
		return true;
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
