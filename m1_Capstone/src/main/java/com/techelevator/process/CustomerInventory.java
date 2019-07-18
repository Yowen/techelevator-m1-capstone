package com.techelevator.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.techelevator.items.Item;

public class CustomerInventory {
	private List<Item> shoppingCart = new ArrayList<Item>();
	private Map<String, Integer> numberOfItemsSold = new TreeMap<String, Integer>();
	private double totalAmountSpent;
	
	public List<Item> getShoppingCart() {
		return shoppingCart;
	}

	public void addToShoppingCart(Item purchasedItem) {
		
		this.shoppingCart.add(purchasedItem);
		
		totalAmountSpent = totalAmountSpent + purchasedItem.getPrice();
		
		if (numberOfItemsSold.containsKey(purchasedItem.getName())) {
			this.numberOfItemsSold.put(purchasedItem.getName(), 
					numberOfItemsSold.get(purchasedItem.getName()) + 1);
		}
		else {
			this.numberOfItemsSold.put(purchasedItem.getName(), 1);
		}
		
	}

	public Map<String, Integer> getNumberItemsSold() {
		
		return numberOfItemsSold;
	}
	
	public double getTotalAmountSpent() {
	
		return totalAmountSpent;
	}
}
