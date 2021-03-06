package battleshipMk2;


public class Tile 
{
	//Attribute Section
	private int tileID;
	private int xCoord;
	private int yCoord;
	private int shipID;
	private int sectionOfShip;
	private boolean hasBeenHit;
	private int shipStatus;
	//End Attribute
	
	//Constructor Section
	public Tile(int tileID, int xCoord, int yCoord, int shipID, int sectionOfShip)
	{
		this.tileID = tileID;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.shipID = 0;
		this.sectionOfShip = sectionOfShip;
		this.hasBeenHit = false;
		this.shipStatus = 0;
	}
	//End Constructor
	
	public Tile(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	//Method Section
	//Get full info from the Tile
	public String getFullContent()
	{
		return "["+ this.tileID +","+ this.xCoord +","+ this.yCoord +","+ this.shipID +","+ this.sectionOfShip+","+hasBeenHit+"]";
	}
	
	//Get the X Coord of the Tile
	public int getXCoord()
	{
		return this.xCoord;
	}
	
	//Get the Y Coord of the Tile
	public int getYCoord()
	{
		return this.yCoord;
	}
	
	//Get the shipID of the Tile
	public int getShipID()
	{
		return this.shipID;
	}
	
	//Get the ship's section of the Tile
	public int getSection()
	{
		return this.sectionOfShip;
	}
	
	//checks if the tile has been hit
	public boolean hasTileHit()
	{
		return this.hasBeenHit;
	}
	
	//Sets the hit status of the tile
	public void setTileHit()
	{
		this.hasBeenHit = true;
	}
	
	//Set the shipID of the tile
	public void setShipID(int shipID)
	{
		this.shipID = shipID;
	}
	
	//Set the ship subsection of the tile
	public void setSection(int section)
	{
		this.sectionOfShip = section;
	}
	
	public void setShipStatus(int newStatus)
	{
		this.shipStatus = newStatus;
	}
	
	public int getShipStatus()
	{
		return this.shipStatus;
	}
	
	public String getContentOfTile()
	{		
		if(shipID == 0 && !this.hasBeenHit)
		{
			return "Empty";
		}
		else if(this.shipStatus == 1)
		{
			return "Sunk";
		}
		else if(this.hasBeenHit && shipID == 0)
		{
			return "Miss";
		}
		else if(shipID != 0 && !this.hasBeenHit && this.shipStatus != 1)
		{
			return shipID+"";
		}
		else if(shipID != 0 && hasBeenHit && this.shipStatus != 1)
		{
			return "Hit";
		}
		return "Empty";
	}
	//End Method
}
