package battleshipMk2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;


public class Computer
{
	//Attribute Section
	private Board aiBoard;
	private Board enemyBoard;
	private int comScore;
	private ArrayList<Tile> tilesThatAreHit = new ArrayList<Tile>();
	private ArrayList<Tile> tilesThatAreMiss = new ArrayList<Tile>();
	private ArrayList<Tile> tilesThatAreSunk = new ArrayList<Tile>();
	private ArrayList<Tile> bannedTiles = new ArrayList<Tile>();
	//End Attribute
	
	//Constructor Section
	public Computer(Board aiBoard, Board enemyBoard)
	{
		this.aiBoard = aiBoard;
		this.enemyBoard = enemyBoard;
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
	
	//Sets computer score
	public void setComScore(int comScore)
	{
		this.comScore = comScore;
	}
	
	//Gets computer score
	public int getComScore()
	{
		return this.comScore;
	}
	
	public void fire()
	{
		Tile selectedTile = null;
		//Step 1: Check for Hit Tiles
		if(isThereAnyHitTiles())
		{
			//Step 1A: If there is a hit tile available, then pick one at random and check if its banned
			int randomNumberToPickTileWith = ThreadLocalRandom.current().nextInt(0, this.tilesThatAreHit.size());
			Tile randomHitTile = tilesThatAreHit.get(randomNumberToPickTileWith);
			
			while(isTileBanned(randomHitTile))
			{
				tilesThatAreHit.remove(randomNumberToPickTileWith);
				randomNumberToPickTileWith = ThreadLocalRandom.current().nextInt(0, this.tilesThatAreHit.size());
				randomHitTile = tilesThatAreHit.get(randomNumberToPickTileWith);
			}
			
			
			//Step 1B: With a random hit tile selected, check the surrounding tiles for available tiles
			Tile surroundingTilePicked = pickASurroundingTile(randomHitTile.getXCoord(), randomHitTile.getYCoord());
			
			//Step 1C: Check to make sure the tile picked isn't null, if it is, reroll the random tile to hit
			while(surroundingTilePicked == null)
			{		
				//Step 1Ci: If there is a hit tile available, then pick one at random
				randomNumberToPickTileWith = ThreadLocalRandom.current().nextInt(0, this.tilesThatAreHit.size());
				randomHitTile = tilesThatAreHit.get(randomNumberToPickTileWith);
				
				//Step 1Cii: With a random hit tile selected, check the surrounding tiles for available tiles
				surroundingTilePicked = pickASurroundingTile(randomHitTile.getXCoord(), randomHitTile.getYCoord());	
			
			}
			
			//Step 1D: With the hit tile selected and verified to be empty, assign it to the general selection
			selectedTile = surroundingTilePicked;
		}
		
		//Step 2: With no hit tiles available, then fire at random
		else
		{
			
			//Step 2A: Select a random Tile on the board to fire at
			Tile randomFireTile = fireAtRandom();
			
			//Step 2B: Fire at target
			selectedTile = randomFireTile;
		}
		
		//Step 3: Fire at the general selected tile
		fireAtTarget(selectedTile);
		
		//Step 3: Check if the AI hit a ship or not
		didTheAIHit(selectedTile);
		
		//Step 4: Recheck the AI's internal logic on which tiles are sunk or not
		removeHitTilesAfterSinking();
		
		//Step 5: There is a infintely small chance of the AI mistaking a minesweeper for a blown up ship if the circumstances are just perfect
		checkForAnomlies();
		
		//So the AI will pick a tile with H on it as a starting point
		//Check each tile around it for a M, in which case it will ignore that direction
		//If two H tiles are in a row, it will want to target the next tile in line
		//if no H tiles are present, then pick tile at random
		//Make the AI not pick a tile that is already fired at
		
		
	}
	
	public Tile fireAtRandom()
	{
		int randomXCoord = ThreadLocalRandom.current().nextInt(0, this.enemyBoard.getLengthOfBoard());
		int randomYCoord = ThreadLocalRandom.current().nextInt(0, this.enemyBoard.getHeightOfBoard());
		while(!isTileSunkenOrMiss(randomXCoord, randomYCoord))
		{
			System.out.println("AI needs to reroll its selection");
			randomXCoord = ThreadLocalRandom.current().nextInt(0, this.enemyBoard.getLengthOfBoard());
			randomYCoord = ThreadLocalRandom.current().nextInt(0, this.enemyBoard.getHeightOfBoard());			
		}
		return aiBoard.getTile(randomXCoord, randomYCoord);
		
	}
	
	//AI picks and fires at a target
	public void fireAtTarget(Tile tileToFireAt)
	{
		int xCoord = tileToFireAt.getXCoord();
		int yCoord = tileToFireAt.getYCoord();
		System.out.println("The AI is firing at " + xCoord + ", " + yCoord);
		enemyBoard.fireAtTile(xCoord, yCoord);
		//return aiBoard.getTile(xCoord, yCoord).getContentOfTile();		
	}
	
	public void didTheAIHit(Tile tileToCheck)
	{
		int xCoord = tileToCheck.getXCoord();
		int yCoord = tileToCheck.getYCoord();
		String contentOfTile = enemyBoard.getTile(xCoord, yCoord).getContentOfTile();
		if(contentOfTile.equalsIgnoreCase("Hit"))
		{
			tilesThatAreHit.add(tileToCheck);	
		}
	}
	
	//Check if tile is banned
	public boolean isTileBanned(Tile tileToCheck)
	{
		for(int i = 0; i < bannedTiles.size(); i++)
		{
			if(bannedTiles.get(i).getXCoord() == tileToCheck.getXCoord() && bannedTiles.get(i).getYCoord() ==  tileToCheck.getYCoord())
			{
				return true;
			}
		}
		return false;
	}
	
	//Records the content of the tile internally
	public void recordTileContent(int xCoord, int yCoord, String contentOfTile)
	{		
		if(contentOfTile.equalsIgnoreCase("Empty"))
		{
			
		}
	}
	
	//Check if there is any hitTiles on the board
	public boolean isThereAnyHitTiles()
	{
		if(tilesThatAreHit.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Simple method to try and introduce a little smarter AI
	public int whatIsOpposingNum(int numToCheck)
	{
		if(numToCheck == 0)
		{
			return 2;
		}
		if(numToCheck == 2)
		{
			return 0;
		}
		if(numToCheck == 1)
		{
			return 3;
		}
		if(numToCheck == 3)
		{
			return 1;
		}
		System.out.println("Error in Opposing Number Generator");
		return 0;
	}
	
	public void removeHitTilesAfterSinking()
	{
		for(int i = 0; i < tilesThatAreHit.size(); i++)
		{
			if(tilesThatAreHit.get(i).getContentOfTile().equalsIgnoreCase("Sunk"))
			{
				tilesThatAreSunk.add(tilesThatAreHit.get(i));
				tilesThatAreHit.remove(i);
			}
		}
	}
	
	public void checkForAnomlies()
	{
		for(int i = 0; i < this.enemyBoard.getTilesOnBoard().size(); i++)
		{
			if(this.enemyBoard.getTilesOnBoard().get(i).getContentOfTile() == "Hit")
			{
				for(int x = 0; x < tilesThatAreHit.size(); x++)
				{
					if(tilesThatAreHit.get(x).getXCoord() != this.enemyBoard.getTilesOnBoard().get(i).getXCoord() && tilesThatAreHit.get(x).getYCoord() != this.enemyBoard.getTilesOnBoard().get(i).getYCoord())
					{
						tilesThatAreHit.add(this.enemyBoard.getTilesOnBoard().get(i));
					}
				}
			}
		}
	}
	
	//Selects a single tile, and checks around it to see if any tiles can be hit
	public Tile pickASurroundingTile(int xCoord, int yCoord)
	{
		System.out.println("The AI is using Tile: " + xCoord + ", " + yCoord + " as the center point" );
		ArrayList<Tile> contentOfNeighbouringTiles = new ArrayList<Tile>();
		//Step 1: Check each direction around the tile in question to see what its content is		
		contentOfNeighbouringTiles.add(this.enemyBoard.getTile(xCoord, yCoord-1)); //0 North
		contentOfNeighbouringTiles.add(this.enemyBoard.getTile(xCoord+1, yCoord)); //1 East
		contentOfNeighbouringTiles.add(this.enemyBoard.getTile(xCoord, yCoord+1)); //2 South
		contentOfNeighbouringTiles.add(this.enemyBoard.getTile(xCoord-1, yCoord)); //3 West
		
		//Step 2: Check each tile for its content
		for(int i = 0; i < contentOfNeighbouringTiles.size(); i++)
		{
//			if(aiBoard.isTileOutOfBounds(contentOfNeighbouringTiles.get(i)))
//			{
//				return null;
//			}
			
			//Check for useless hit tiles
			if(contentOfNeighbouringTiles.get(whatIsOpposingNum(i)) != null && contentOfNeighbouringTiles.get(i) != null && contentOfNeighbouringTiles.get(whatIsOpposingNum(i)).getContentOfTile().equalsIgnoreCase("Hit") && !contentOfNeighbouringTiles.get(i).getContentOfTile().equalsIgnoreCase("Hit"))
			{
				System.out.println("This Tile is useless, remove it from the board");
				for(int y = 0; y < tilesThatAreHit.size(); y++)
				{
					if(tilesThatAreHit.get(y).getXCoord() == xCoord && tilesThatAreHit.get(y).getYCoord() == yCoord)
					{
						tilesThatAreHit.remove(y);
					}
				}
				
			}
			
			//Check the opposing number to see if a line is possible
			if(contentOfNeighbouringTiles.get(whatIsOpposingNum(i)) != null && contentOfNeighbouringTiles.get(i) != null && contentOfNeighbouringTiles.get(whatIsOpposingNum(i)).getContentOfTile().equalsIgnoreCase("Hit") && !contentOfNeighbouringTiles.get(i).hasTileHit())					
			{
				System.out.println("Line Attack might be possible");
				return contentOfNeighbouringTiles.get(i);
			}
			
			//Check for a non-line attack
			if(contentOfNeighbouringTiles.get(i) != null && !contentOfNeighbouringTiles.get(i).getContentOfTile().equalsIgnoreCase("Sunk") && !contentOfNeighbouringTiles.get(i).getContentOfTile().equalsIgnoreCase("Miss") && !contentOfNeighbouringTiles.get(i).hasTileHit())
			{
				//Step 3: Return the tile if its content equals empty
				System.out.println("Firing without line attack");
				return contentOfNeighbouringTiles.get(i);
			}
		}
		
		//Step 3A: Return null if no tiles are available
		System.out.println("Cannot find any tiles to hit");
		try {
			//Come back to this bit, you need to work out a way to remove tiles from the hit list and make the AI revert to random targetting
			System.out.println("Size of Tiles Hit" + tilesThatAreHit.size());
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//Checks if the AI picked a sunken or missed tile
	public boolean isTileSunkenOrMiss(int xCoord, int yCoord)
	{
		if(enemyBoard.getTile(xCoord, yCoord).getContentOfTile().equalsIgnoreCase("Miss"))
		{
			tilesThatAreMiss.add(this.enemyBoard.getTile(xCoord, yCoord));
		}
		if(enemyBoard.getTile(xCoord, yCoord).getContentOfTile().equalsIgnoreCase("Sunk"))
		{
			tilesThatAreSunk.add(this.enemyBoard.getTile(xCoord, yCoord));
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
	
	//Gets the AI's board
	public Board getAIBoard()
	{
		return this.aiBoard;
	}
	
	//Gets what the AI can see
	public void getAIBoardPrintOut()
	{
		this.aiBoard.printOutGamePlayBoard();
	}
	//End Method
}
