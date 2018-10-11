package battleshipMk2;

import java.util.Scanner;

public class Main {
	//Attribute Section 
	private Scanner inputScanner = new Scanner(System.in);
	//End Attribute
	
	//Method Section
	public static void main(String[] args) 
	{
		Board board = new Board();
		//board.addPlayerShipToBoard();
		//board.addAIShipToBoard("Ship 1", 5, 2, 0, 0);
		board.printOutBoard();
		Computer computerPlayer1 = new Computer(board);
		
		Player player1 = new Player();
		
		while(board.shipsExist())
		{
			System.out.println("Please specify an X coordinate");
			int xCoord = player1.getPlayerCoord();
			System.out.println("Please specify an Y coordinate");
			int yCoord = player1.getPlayerCoord();		
			board.fireAtTile(xCoord, yCoord);
		}
		
		
	}

	
	public String getUserStringInput()
	{
		return inputScanner.next();
	}
	
	public int getUserIntInput()
	{
		while(true)
		{
			try
			{
				return Integer.parseInt(this.inputScanner.next());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Input. Please try again");
			}
		}
	}
}
