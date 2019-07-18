package com.techelevator;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.items.Candy;
import com.techelevator.items.Chip;
import com.techelevator.items.Gum;
import com.techelevator.items.Item;
import com.techelevator.process.CustomerInventory;



public class CustomerInventoryTest {

	private CustomerInventory customerInventory;
	
	
	@Before
	public void setup() {
		customerInventory = new CustomerInventory();
	}
	
	@Test
	public void can_add_item_to_shopping_cart_and_get_it() {
		Item item = new Candy("A1", "Kitkat", 1.00, 5);
		customerInventory.addToShoppingCart(item);
		List<Item> shoppingCart = customerInventory.getShoppingCart();
		
		String result = shoppingCart.get(0).getName();
		
		Assert.assertEquals("Kitkat", result);
	}
	
	@Test
	public void number_of_items_sold_returns_number_of_item_sold_to_customer() {
		Item item = new Candy("A1", "Kitkat", 1.00, 5);
		
		
		customerInventory.addToShoppingCart(item);
		customerInventory.addToShoppingCart(item);
		customerInventory.addToShoppingCart(item);
		
		Map<String, Integer> numberOfItemsSold = customerInventory.getNumberItemsSold();
		
		int result = numberOfItemsSold.get("Kitkat");
		
		Assert.assertEquals(3, result);
	}
	
	@Test
	public void total_amount_spent_returns_correct_amount_spent() {
		Item item = new Candy("A1", "Kitkat", 1.00, 5);
		Item item2 = new Gum("B1", "Chewy", 2.20, 5);
		Item item3 = new Chip("C2", "Kitkat", 1.05, 5);
		Item item4 = new Gum("G1", "Bum", 0.35, 5);
		
		
		customerInventory.addToShoppingCart(item);
		customerInventory.addToShoppingCart(item2);
		customerInventory.addToShoppingCart(item3);
		customerInventory.addToShoppingCart(item4);
		
		double result = customerInventory.getTotalAmountSpent();
		
		Assert.assertEquals(4.60, result, 2);
	}
	
	
}
