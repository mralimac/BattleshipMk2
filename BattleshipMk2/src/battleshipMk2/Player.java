package battleshipMk2;

public class Player extends Main
{
	//Attribute Section
	private String playerName;
	private int playerScore;
	private int shipsAllowed;
	private int shipsPlaced;
	private Board playerBoard;
	private Board enemyBoard;
	//End Attribute
	
	//Constructor Section	
	public Player(String playerName, Board playerBoard, Board enemyBoard)
	{
		this.playerName = playerName;
		this.playerScore = 0;
		this.shipsAllowed = 5;
		this.playerBoard = playerBoard;
		this.enemyBoard = enemyBoard;
	}
	public Player(String playerName, Board playerBoard, Board enemyBoard, int shipsAllowed)
	{
		this.playerName = playerName;
		this.playerScore = 0;
		this.shipsAllowed = shipsAllowed;
		this.playerBoard = playerBoard;
		this.enemyBoard = enemyBoard;
	}
	//End Constructor
	
	//Method Section
	
	//Fires at AI ship
	public void fire()
	{
		System.out.println("Please specify an X coordinate");
		int xCoord = getPlayerCoord();
		System.out.println("Please specify an Y coordinate");
		int yCoord = getPlayerCoord();		
		enemyBoard.fireAtTile(xCoord, yCoord);
	}
	
	
	//Asks the player for coords
	public int getPlayerCoord()
	{
		return getUserIntInput();
	}
	
	//Return players score
	public int getPlayerScore()
	{
		return this.playerScore;
	}

	//Sets players score
	public void setPlayerScore(int playerScore)
	{
		this.playerScore = playerScore;
	}
	
	//Return players name
	public String getPlayerName()
	{
		return this.playerName;
	}	
	
	public int getShipPlaced()
	{
		return this.shipsPlaced;
	}
	
	//Place a ship
	public void placeShips()
	{
		for(int i = 0; i < this.shipsAllowed; i++)
		{
			playerBoard.printOutGamePlayBoard();
			playerBoard.addPlayerShipToBoard(i+1);
		}
	}	
	
	
	//End Method
}
