package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleshipMk2.Ship;

class ShipTestCase {

	
	
	@Test
	void createNewShip()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1);
		if(testShip1.getShipID() < 0)
		{
			fail("No Ship Generated");
		}
	}
	
	@Test
	void testGetShipHealth()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1);
		int testShip1Health = testShip1.getShipHealth();
		if(testShip1Health < 0)
		{
			fail("Incorrect Ship Health");
		}
		
	}
	
	@Test
	void testGetShipType()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1);
		String shipType = testShip1.getShipTypeString();
		if(!shipType.equalsIgnoreCase("Minesweeper"))
		{
			fail("Incorrect Ship Returned");
		}
	}
}
