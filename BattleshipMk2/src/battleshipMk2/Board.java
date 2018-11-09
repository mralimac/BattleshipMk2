package battleshipMk2;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Board extends Main
{
	//Attribute Section
	private ArrayList<Tile> tilesOnBoard = new ArrayList<Tile>();
	private ArrayList<Ship> shipsOnBoard = new ArrayList<Ship>();
	private int lengthOfBoard;
	private int heightOfBoard;	
	//End Attribute
	
	//Constructor Section
	public Board()
	{
		this.lengthOfBoard = 7;
		this.heightOfBoard = 7;
		addTilesToBoard(lengthOfBoard, heightOfBoard);
	}
	
	public Board(int lengthOfBoard, int heightOfBoard)
	{
		this.lengthOfBoard = lengthOfBoard;
		this.heightOfBoard = heightOfBoard;
		addTilesToBoard(lengthOfBoard, heightOfBoard);
	}
	//End Constructor
	
	//Method Section
	
	//Populates the board object with tile objects
	public void addTilesToBoard(int lengthOfBoard, int heightOfBoard)
	{
		int xCoord = 0;
		int yCoord = 0;
		for(int i = 0; i < (lengthOfBoard * heightOfBoard); i++)
		{
			if(xCoord == lengthOfBoard)
			{
				xCoord = 0;
				yCoord++;
			}
			tilesOnBoard.add(new Tile(i, xCoord, yCoord, 0, 0));
			xCoord++;
		}
	}
	
	//Adds an AI ship to the board
	public void addAIShipToBoard(String shipName, int shipType)
	{	
		int shipLength = 0;
		switch(shipType)
		{
		case 1: shipLength = 1;
		break;
		case 2: shipLength = 3;
		break;
		case 3: shipLength = 3;
		break;
		case 4: shipLength = 4;
		break;
		case 5: shipLength = 5;
		break;
		}	
		int randomXCoord = ThreadLocalRandom.current().nextInt(0, this.lengthOfBoard);
		int randomYCoord = ThreadLocalRandom.current().nextInt(0, this.heightOfBoard);
		//check all 4 directions if AI ship placement is valid
		while(canAIPlaceShip(randomXCoord, randomYCoord, shipLength) == 0)
		{
			System.out.println("Reroll the AI ship placement");
			randomXCoord = ThreadLocalRandom.current().nextInt(0, this.lengthOfBoard);
			randomYCoord = ThreadLocalRandom.current().nextInt(0, this.heightOfBoard);
		}
		int vaildDirection = canAIPlaceShip(randomXCoord, randomYCoord, shipLength);
		
		
		int shipID = shipsOnBoard.size() + 1;
		placeShipInTiles(randomXCoord, randomYCoord, shipID, vaildDirection, shipLength);
		shipsOnBoard.add(new Ship(shipID, shipType, shipLength, vaildDirection, randomXCoord, randomYCoord, shipName));
		System.out.println("The AI successfully placed a ship");
		System.out.println("AI Ship Details:\n Ship ID: " + shipID + "\nShipType: " + shipType + "\nShipLength: "+ shipLength + "\nShip Direction: " + vaildDirection + "\nX and Y Coord: " + randomXCoord + "," + randomYCoord);
	}
	
	//Adds a players ship to the board
	public void addPlayerShipToBoard()
	{
		//Ask for the ships name
		System.out.println("Please enter a ship name");		
		String shipName = getUserStringInput();
		
		//Ask for the ships type which determines its length and health
		System.out.println("Please enter a ship type");
		System.out.println("1 - Minesweeper");
		System.out.println("2 - Corvette");
		System.out.println("3 - Submarine");
		System.out.println("4 - Battleship");
		System.out.println("5 - Aircraft Carrier");
		int shipType = getUserIntInput();
		int shipLength = 0;
		switch(shipType)
		{
		case 1: shipLength = 1;
		break;
		case 2: shipLength = 3;
		break;
		case 3: shipLength = 3;
		break;
		case 4: shipLength = 4;
		break;
		case 5: shipLength = 5;
		break;		
		}
		
		//asks which direction the ship is facing
		System.out.println("Which direction is in the ship facing?");
		System.out.println("1 - North");
		System.out.println("2 - East");
		System.out.println("3 - South");
		System.out.println("4 - West");
		int shipDirection = getUserIntInput();
		
		//Asks for the ships xcoord
		System.out.println("What is the XCoord of the ship's bow?");
		int shipBowXCoord = getUserIntInput();		
		
		//Asks for the ships ycoord
		System.out.println("What is the YCoord of the ship's bow?");
		int shipBowYCoord = getUserIntInput();
				
		//Checks if the ship can be placed. if not, it asks the user to change place/direction of the ship
		while(!canShipBePlaced(shipBowXCoord, shipBowYCoord, shipDirection, shipLength))
		{
			System.out.println("This ship is not placeable, please pick another coord or direction");
			
			System.out.println("What is the XCoord of the ship's bow?");
			shipBowXCoord = getUserIntInput();
			
			System.out.println("What is the YCoord of the ship's bow?");
			shipBowYCoord = getUserIntInput();
			
			System.out.println("Which direction is in the ship facing?");
			System.out.println("1 - North");
			System.out.println("2 - East");
			System.out.println("3 - South");
			System.out.println("4 - West");			
			shipDirection = getUserIntInput();
		}
		
		//This finally places the ship on the board, and creates an object for it
		int shipID = shipsOnBoard.size() + 1;
		placeShipInTiles(shipBowXCoord, shipBowYCoord, shipID, shipDirection, shipLength);
		shipsOnBoard.add(new Ship(shipID, shipType, shipLength, shipDirection, shipBowXCoord, shipBowYCoord, shipName));
	}
	
	//This method assigns the ships and its subsections to tiles
	public void placeShipInTiles(int xCoord, int yCoord, int shipID, int shipDirection, int shipLength)
	{
		for(int i = 0; i < shipLength; i++)
		{
			getTile(xCoord, yCoord).setShipID(shipID);
			getTile(xCoord, yCoord).setSection(i);
			
			switch(shipDirection)
			{
			case 1: yCoord--;
			break;
			case 2: xCoord++;
			break;
			case 3: yCoord++;			
			break;
			case 4: xCoord--;
			break;
			}
		}
	}
	
	public int canAIPlaceShip(int xCoord, int yCoord, int shipLength)
	{
		for(int i=1; i < 5; i++)
		{
			if(canShipBePlaced(xCoord, yCoord, i, shipLength))
			{
				return i;
			}
		}
		return 0;
	}
	
	//Checks if ships still exist on the board
	public boolean shipsExist()
	{
		if(shipsOnBoard.size() < 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//Runs if a ship is sunk, and removes it from the game
	public void shipSunk(int shipID)
	{
		for(int i = 0; i < shipsOnBoard.size(); i ++)
		{
			if(shipsOnBoard.get(i).getShipID() == shipID)
			{
				shipsOnBoard.remove(i);
			}
		}
		
	}
	
	//Runs when a shot is fired, this blows up a tile, and damages/sinks a ship if it was on that tile
	public void fireAtTile(int xCoord, int yCoord)
	{
		Tile tile = getTile(xCoord, yCoord);
		if(!tile.hasTileHit())
		{
			tile.setTileHit();
			if(tile.getShipID() != 0)
			{
				int shipID = tile.getShipID();
				int shipSection = tile.getSection();
				Ship shipGettingHit = getShip(shipID);
				shipGettingHit.hitShip(shipSection);		

				System.out.println(shipGettingHit.getShipName() +" ( "+ shipGettingHit.getShipTypeString() +" ) has been hit!");
				System.out.println("Remaining Health of " + shipGettingHit.getShipName() + ":" + shipGettingHit.getShipHealth() + "/" + shipGettingHit.getShipLength());
				if(shipGettingHit.isShipSunk())
				{
					System.out.println(shipGettingHit.getShipName() + " has been sunk!");
				}
			}
			else
			{
				System.out.println("Miss!");
			}
		}
		else
		{
			System.out.println("You've already fired here!");
		}
	}
	
	//Checks if the ship can be placed on the coords that were chosen
	public boolean canShipBePlaced(int xCoord, int yCoord, int shipDirection, int shipLength)
	{
		if(isShipOutOfBounds(xCoord, yCoord))
		{
			return false;
		}
		
		if(getTile(xCoord, yCoord).getShipID() != 0)
		{
			return false;
		}		
		
		for(int i = 0; i < shipLength; i++)
		{
			switch(shipDirection)
			{
			case 1: yCoord--;
			break;
			case 2: xCoord++;
			break;
			case 3: yCoord++;			
			break;
			case 4: xCoord--;
			break;
			}			
			
			if(isShipOutOfBounds(xCoord, yCoord))
			{
				return false;
			}
			System.out.println("Attempted to check coords: " + xCoord + "," + yCoord);
			if(xCoord < 0 || xCoord > this.lengthOfBoard || yCoord < 0 || yCoord > this.heightOfBoard)
			{
				if(getTile(xCoord, yCoord).getShipID() != 0)
				{
					return false;
				}
			}
		}	
		
		return true;
	}

	//Checks if the ship is out of bounds
	public boolean isShipOutOfBounds(int xCoord, int yCoord)
	{
		if(xCoord < 0 || xCoord > this.lengthOfBoard || yCoord < 0 || yCoord > this.heightOfBoard) 
		{
			return true;
		}
		return false;
	}
	
	//Returns the tile object at the specified coords
	public Tile getTile(int xCoord, int yCoord)
	{
		for(int i = 0; i < tilesOnBoard.size(); i++)
		{
			if(tilesOnBoard.get(i).getXCoord() == xCoord && tilesOnBoard.get(i).getYCoord() == yCoord)
			{
				return tilesOnBoard.get(i);
			}
		}
		System.out.println("This is not a valid tile!");
		return null;
	}
	
	//Returns the ship object for the specified shipID number	
	public Ship getShip(int shipID)
	{
		for(int i = 0; i < shipsOnBoard.size(); i ++)
		{
			if(shipsOnBoard.get(i).getShipID() == shipID)
			{
				return shipsOnBoard.get(i);
			}
		}
		return null;
	}
	
	//A debug function to print out tile info for specified coords
	public void printOutTileInfo(int x, int y)
	{
		Tile tile = getTile(x, y);		
		Ship shipInTile = getShip(tile.getShipID());
		int shipSection = tile.getSection();
		String shipType = shipInTile.getShipTypeString();
		
		System.out.println("Ship Name is: " + shipType);
		System.out.println("Section of Ship is: " + shipSection);
	}
	
	//Gets number of ships on the board
	public void numOfShips()
	{
		System.out.println("Ships: " + shipsOnBoard.size());
	}
	
	//Prints out a debug formatted gameboard 
	public void printOutBoard()
	{
		int tileCounter = 0;
		for(int i = 0; i < this.heightOfBoard; i++)
		{			
			String tileOutput = "";
			for(int e = 0; e < this.lengthOfBoard; e++)
			{				
				tileOutput = tileOutput + tilesOnBoard.get(tileCounter).getFullContent();

				tileCounter++;
			}
			System.out.println(tileOutput);			
		}
	}
	
	//Prints out a better formatted board
	public void printOutGamePlayBoard()
	{
		int tileCounter = 0;
		for(int i = 0; i < this.heightOfBoard; i++)
		{			
			String tileOutput = "";
			for(int e = 0; e < this.lengthOfBoard; e++)
			{				
				tileOutput = tileOutput + formatValues(tileCounter);				
				tileCounter++;
			}
			System.out.println(tileOutput);			
		}
	}
	
	//Parse values into formatting string
	public String formatValues(int tileCounter)
	{
		int tileShipID = tilesOnBoard.get(tileCounter).getShipID();
		boolean hasThisTileBeenHit = tilesOnBoard.get(tileCounter).hasTileHit();
		if(tileShipID == 0 && !hasThisTileBeenHit)
		{
			return "[0]";
		}
		else if(hasThisTileBeenHit && tileShipID == 0)
		{
			return "[M]";
		}
		else if(tileShipID != 0 && !hasThisTileBeenHit)
		{
			return "[S]";
		}
		else if(tileShipID != 0 && hasThisTileBeenHit)
		{
			return "[H]";
		}
		return "[E]";
		
	}
	//End Method
	
}
