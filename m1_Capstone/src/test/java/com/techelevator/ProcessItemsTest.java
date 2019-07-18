package com.techelevator;

import org.junit.Before;
import org.junit.Test;
import com.techelevator.items.Item;
import com.techelevator.process.VendingMachineInventory;

import java.util.Map;

import org.junit.Assert;

public class ProcessItemsTest {

	private VendingMachineInventory processItems;

	@Before
	public void setup() {
		processItems = new VendingMachineInventory();
	}

	@Test
	public void if_enter_A1_get_potato_crisp() {
	    Map<String, Item> inventory = processItems.getInventory();
		Item item = inventory.get("B2");
		String actual = item.getName();
		Assert.assertEquals("Cowtales", actual);
	}

	@Test
	public void if_candy_item_makes_candy_item() {
	
		Map<String, Item> inventory = processItems.getInventory();
		Item item = inventory.get("B1");
		String actual = item.getType();
		Assert.assertEquals("Candy", actual);
	}

	@Test
	public void if_chip_item_makes_chip_item() {
		Map<String, Item> inventory = processItems.getInventory();
		Item item = inventory.get("A1");
		String actual = item.getType();
		Assert.assertEquals("Chip", actual);
	}

	@Test
	public void item_fed_to_map_returnable_by_key() {
		Map<String, Item> inventory = processItems.getInventory();
		Item item = inventory.get("C4");
		String actual = item.getName();
		Assert.assertEquals("Heavy", actual);
	}
	
	@Test
	public void checking_initial_item_count() {
	
		Map<String, Item> inventory = processItems.getInventory();
		Item item = inventory.get("C4");
		int actual = item.getCount();
		Assert.assertEquals(5, actual);
	}

}
