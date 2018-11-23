package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleshipMk2.Ship;

class ShipTestCase {

	
	
	@Test
	void createNewShip()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1, "Hi");
		if(testShip1.getShipID() < 0)
		{
			fail("No Ship Generated");
		}
	}
	
	@Test
	void testGetShipHealth()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1, "Hi");
		int testShip1Health = testShip1.getShipHealth();
		if(testShip1Health < 0)
		{
			fail("Incorrect Ship Health");
		}
		
	}
	
	@Test
	void testGetShipType()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1, "Hi");
		String shipType = testShip1.getShipTypeString();
		if(!shipType.equalsIgnoreCase("Minesweeper"))
		{
			fail("Incorrect Ship Returned");
		}
	}
	
	@Test
	void testChangeShipName()
	{
		Ship testShip1 = new Ship(0, 1, 1, 1, 1, 1, "Hi");
		testShip1.setShipName("New Ship Name");
		if(!testShip1.getShipName().equalsIgnoreCase("New Ship Name"))
		{
			fail("Ship Name is incorrect");
		}
	}

}
