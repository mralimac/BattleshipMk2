package battleshipMk2;

import java.util.Scanner;

public class Main {
	//Attribute Section 
	private static Scanner inputScanner = new Scanner(System.in);
	//End Attribute
	
	//Constructor Section
	//End Constructor
	
	//Method Section
	public static void main(String[] args) 
	{
		//Board board = new Board();	
		Board aiBoard = new Board();
		//Board aiBoard2 = new Board();
		//board.printOutBoard();
		Computer comPlayer1 = new Computer(aiBoard);
		//Computer comPlayer2 = new Computer(aiBoard2);
		
		Player player1 = new Player("Ali");		
		//board.addPlayerShipToBoard();
		//board.numOfShips();
		//comPlayer.getAIBoardPrintOut();
		//board.printOutGamePlayBoard();
		aiBoard.printOutGamePlayBoard();
		System.out.println("");
		//aiBoard2.printOutGamePlayBoard();
		
		while(aiBoard.shipsExist())
		{
			System.out.println("Please specify an X coordinate");
			int xCoord = player1.getPlayerCoord();
			System.out.println("Please specify an Y coordinate");
			int yCoord = player1.getPlayerCoord();		
			aiBoard.fireAtTile(xCoord, yCoord);
			aiBoard.printOutGamePlayBoard();
		}
		System.out.println("Gameover!");
		scannerClose();
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
				return Integer.parseInt(inputScanner.next());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Input. Please try again");
			}
		}
	}
	
	public static void scannerClose()
	{
		inputScanner.close();
	}
}
