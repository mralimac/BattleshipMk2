package battleshipMk2;

import java.util.ArrayList;

public class Ship 
{
	//Attribute Section
	private int shipID;
	private int typeOfShip;
	private int healthOfShip;
	private int lengthOfShip;
	private int shipDirection;
	private int xCoord;
	private int yCoord;
	private String shipName;
	private ArrayList<Integer> shipSections = new ArrayList<Integer>();
	//End Attribute
	
	//Constructor Section
	public Ship(int shipID, int typeOfShip, int lengthOfShip, int shipDirection, int xCoord, int yCoord, String shipName)
	{
		this.shipID = shipID;
		this.typeOfShip = typeOfShip;
		this.lengthOfShip = lengthOfShip;
		this.shipDirection = shipDirection;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.healthOfShip = lengthOfShip;
		this.shipName = shipName;
		for(int i = 0; i < lengthOfShip; i++) 
		{
			shipSections.add(0);
		}
	}
	//End Constructor
	
	//Method Section
	
	//Returns the name of the ship as a String
	public String getShipTypeString()
	{
		switch(typeOfShip)
		{
		case 1: return "Minesweeper";
		case 2: return "Corvette";
		case 3: return "Submarine";
		case 4: return "Battleship";
		case 5: return "Aircraft Carrier";
		}
		return null;
	}
	
	//Returns the ship's ID number
	public int getShipID()
	{
		return this.shipID;
	}
	
	//Returns the ship's name
	public String getShipName()
	{
		return this.shipName;
	}
	
	//Returns the ship's length	
	public int getShipLength()
	{
		return this.lengthOfShip;
	}
	
	//Returns the ship's health
	public int getShipHealth() 
	{
		return this.healthOfShip;
	}
	
	//Adjusts the ship's health and destroys a section of the ship
	public void hitShip(int sectionOfShip)
	{
		if(shipSections.get(sectionOfShip) != 1)
		{
			shipSections.set(sectionOfShip, 1);
			this.healthOfShip--;
		}
	}
	
	//Checks if the ship is sunk 
	public boolean isShipSunk()
	{
		for(int i = 0; i < shipSections.size(); i++)
		{
			if(shipSections.get(i) == 0)
			{
				return false;
			}
		}
		return true;
	}
	//End Method
}
