package com.techelevator.process;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.techelevator.items.Candy;
import com.techelevator.items.Chip;
import com.techelevator.items.Drink;
import com.techelevator.items.Gum;
import com.techelevator.items.Item;

public class VendingMachineInventory {

	private FileReader fileReader = new FileReader("vendingmachine.csv");
	private List<String[]> fileContents = fileReader.getFileContents();
	private Map<String, Item> listOfItems;
	Map<String, Integer> numberOfItemsSold;
	
	private int count = 5;

	public Map<String, Item> getInventory() {
		listOfItems = new TreeMap<String, Item>();

		for (String[] item : fileContents) {
			
			double price = Double.parseDouble(item[2]);
			
			if (item[3].equals("Candy")) {
				Candy candy = new Candy(item[0], item[1], price, count);
				listOfItems.put(candy.getKey(), candy);
			}	
			if (item[3].equals("Chip")) {
				Chip chip = new Chip(item[0], item[1], price, count);
				listOfItems.put(chip.getKey(), chip);		
			}
			if (item[3].equals("Drink")) {
				Drink drink = new Drink(item[0], item[1], price, count);
				listOfItems.put(drink.getKey(), drink);			
			}		
			if (item[3].equals("Gum")) {
				Gum gum = new Gum(item[0], item[1], price, count);
				listOfItems.put(gum.getKey(), gum);	
			}
		}
		return listOfItems;
	}
	
	
	
//	public void resetNumberOfItemsSold () {
//		
//		this.numberOfItemsSold = new TreeMap<String, Integer>();
//		
//	}

}