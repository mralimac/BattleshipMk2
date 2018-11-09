package battleshipMk2;

public class Player extends Main
{
	//Attribute Section
	private String playerName;
	private int playerScore;
	//End Attribute
	
	//Constructor Section	
	public Player()
	{
		
	}
	//End Constructor
	
	//Method Section
	public int getPlayerCoord()
	{
		return getUserIntInput();
	}
	//End Method
}
