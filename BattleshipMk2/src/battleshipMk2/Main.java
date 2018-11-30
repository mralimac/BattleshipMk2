package battleshipMk2;

import java.util.Scanner;

public class Main {
	//Attribute Section 
	private static Scanner inputScanner = new Scanner(System.in);
	//End Attribute
	
	//Constructor Section
	//End Constructor
	
	//Method Section
	public static void main(String[] args) throws InterruptedException 
	{
		//Board board = new Board();	
		Board aiBoard = new Board();
		Board playerBoard = new Board();
		
		//Board aiBoard2 = new Board();
		//board.printOutBoard();
		Computer comPlayer1 = new Computer(aiBoard, playerBoard);		
		//Computer comPlayer2 = new Computer(aiBoard2);
		
		//Player player1 = new Player("Ali", playerBoard, aiBoard, 1);
		Computer player1 = new Computer(playerBoard, aiBoard);
		//player1.placeShips();
		
		while(aiBoard.shipsExist() || playerBoard.shipsExist())
		{			
			player1.fire();
			aiBoard.printOutGamePlayBoard();
			
			comPlayer1.fire();
			playerBoard.printOutGamePlayBoard();
			Thread.sleep(1000);
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
