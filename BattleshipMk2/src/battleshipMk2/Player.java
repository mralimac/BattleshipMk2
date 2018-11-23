package battleshipMk2;

public class Player extends Main
{
	//Attribute Section
	private String playerName;
	private int playerScore;
	//End Attribute
	
	//Constructor Section	
	public Player(String playerName)
	{
		this.playerName = playerName;
		this.playerScore = 0;
	}
	//End Constructor
	
	//Method Section
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
	
	//End Method
}
