package com.techelevator.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.techelevator.view.Menu;



public class SalesPrintOut {
	
	private String fileName = "sales.txt";
	private File file = new File(fileName);
	private FileReader fileReader;
	private List<String[]> fileContents;
	private Menu menu;
	
	public void updateSalesFile(Map<String, Integer> shoppingCartItemsSold, double totalAmountSpent) {
		

		
		if (file.exists() == false) {
			try (PrintWriter writer = new PrintWriter(file)) {

			for (String itemName : shoppingCartItemsSold.keySet()) {
				writer.println(itemName + "|" + shoppingCartItemsSold.get(itemName));
			}
			writer.println("Total Sales|" + totalAmountSpent);

			} catch (FileNotFoundException e) {
				menu.displayMessage("ERROR - File not found!");
			}
			
		}
		
		else {
			fileReader = new FileReader(fileName);
			fileContents = fileReader.getFileContents();
			Map<String, Integer> currentItemsSoldList = new TreeMap<String, Integer>();
			double oldTotalAmount = 0.00;
			
			for (String[] item : fileContents) {
				if (!item[0].equals("Total Sales")) {
				currentItemsSoldList.put(item[0], Integer.parseInt(item[1]));	
			}
				else {
					oldTotalAmount = Double.parseDouble(item[1]);
				}
			}
			for (String item : shoppingCartItemsSold.keySet()) {
				if (currentItemsSoldList.containsKey(item)) {
				int newCount = currentItemsSoldList.get(item) + shoppingCartItemsSold.get(item);
				currentItemsSoldList.put(item, newCount);
				}
				else {
					currentItemsSoldList.put(item, shoppingCartItemsSold.get(item));
				}
			}
			try (PrintWriter writer = new PrintWriter(file)) {
				for (String item : currentItemsSoldList.keySet()) {
					writer.println(item + "|" + currentItemsSoldList.get(item));
				}
				NumberFormat numberFormat = NumberFormat.getInstance();
				writer.println("Total Sales|" + (numberFormat.format(oldTotalAmount + totalAmountSpent)));
				} catch (FileNotFoundException e) {
					menu.displayMessage("ERROR - File not found!");
				}
		
		}
	}
}
//		else {
//			// lines of array
//			// turn to map of item name and amount sold including 0
//			// add any new items and amount sold
//			// method : number of x purchased list
//			
//			for (String itemName : currentItemsSoldList.keySet()) {
//				
//			}
			// method : total money spend
//		}
		
	
//}
