package com.techelevator.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.items.Item;
import com.techelevator.process.CustomerInventory;
import com.techelevator.process.Money;
import com.techelevator.process.VendingMachineInventory;
import com.techelevator.process.SalesPrintOut;
import com.techelevator.process.VendingMachineLogWriter;

public class Menu {
	
	private Scanner in = new Scanner(System.in);
	private VendingMachineInventory inventory = new VendingMachineInventory();
	private Map<String, Item> itemList = inventory.getInventory();
	private Money money =  new Money();
	private CustomerInventory customerInventory = new CustomerInventory();
	private VendingMachineLogWriter log = new VendingMachineLogWriter();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private SalesPrintOut salesPrintOut = new SalesPrintOut();
	private String output;
	private String nextOutput;
	private String floatingDecimalString;
	private String nameKeyPair;
	private String inputSubstring;
	private double fedMoney;

	public void mainMenu() {
		
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.flush();
		String selection = in.nextLine();
		
		// If user selects 1, display all item information
		if (selection.equals("1")) {
			System.out.printf("%-5s %-25s %-5s %-10s\n", "Key:", "Product Name:", "Price:", "Stock:");
			for (String key : itemList.keySet()) {
				// Output changes depending on item stock
				if (itemList.get(key).getCount() == 0) {
					System.out.printf("%-5s %-25s %s %.2f %-10s", key, 
							itemList.get(key).getName(), "$", itemList.get(key).getPrice(), 
							"SOLD OUT!");
					System.out.println();
				}
				else {
					System.out.printf("%-5s %-25s %s %.2f %-10s", key, 
						itemList.get(key).getName(), "$", itemList.get(key).getPrice(), 
						itemList.get(key).getCount());
					System.out.flush();
					System.out.println();
				}
			}
			selection = in.nextLine();
		}
		// If user selects 2, access the sub-menu
		if (selection.equals("2")) {
			this.subMenu();
		}
	}
	
	public void subMenu() {
		
		customerInventory = new CustomerInventory();
		boolean inSubMenu = true;
		while(inSubMenu) {
			
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			System.out.printf("%s %.2f\n", "Current Money Provided: $", money.getCurrentMoney());
			String selection = in.nextLine();
			
			// If user selects 1, prompt the user to provide money
			if (selection.equals("1")) {
				System.out.print("Please provide money in whole dollar amounts (ie. $1, $2, $5, $10, $20): ");
				// Checking for valid input
				String input = in.nextLine();
				if (input.charAt(0) == '$') {
					inputSubstring = input.substring(1);
				}
				else {
					System.out.println("Please provide a valid form of payment (ie. $1, $2, $5, $10, $20)");
					System.out.println();

				}
				
				try {
					fedMoney = Integer.parseInt(inputSubstring);
					double fedMoneyDouble = (double) fedMoney;
					floatingDecimalString = String.format("%.2f", fedMoneyDouble);
					output = "$" + floatingDecimalString;
					floatingDecimalString = String.format("%.2f", money.getCurrentMoney());
					nextOutput = "$" + floatingDecimalString;
					money.setCurrentMoney(fedMoney, false);
					Date date = new Date();
					log.writeFile(String.format("%s %-18s %s %s",formatter.format(date), "FEED MONEY: ", output, nextOutput));
					
				} catch(NumberFormatException e) {
					System.out.println("Please provide a valid form of payment");
//					System.out.println("*** ERROR - Foreign object detected ***");
//					System.out.println("*** ERROR - Coin mechanism is jammed ***");
//					System.out.println("*** ERROR - Vending Machine is shutting down ***");
//					System.exit(0);
				}
			}
			// If user selects 2, prompt the user to select product (key)
			else if (selection.equals("2")) {
				
				Item selectItem = null;
				
				System.out.print("Please provide a product selection: ");
				String productSelection = in.nextLine();
				
				if (itemList.containsKey(productSelection) == false) {
					System.out.println("This product doesn't exist! Please try another item.");
				}
				else {
					selectItem = itemList.get(productSelection);

					if (selectItem.getCount() == 0) {
						System.out.println("This Item is Sold Out! Please try another item.");
					}
					else if (money.getNotEnoughMoney(selectItem.getPrice())) { // selectItem.getPrice() > money.getCurrentMoney()) {
						System.out.println("Not enough money for item! Please add money.\n");
					}
					else {
						double prePurchaseMoney = money.getCurrentMoney();
						floatingDecimalString = String.format("%.2f", prePurchaseMoney);
						output = "$" + floatingDecimalString;
						floatingDecimalString = String.format("%.2f", money.getCurrentMoney());
						nextOutput = "$" + floatingDecimalString;
						nameKeyPair = selectItem.getName() + " " + selectItem.getKey();
						money.setCurrentMoney(selectItem.getPrice(), true);
						selectItem.reduceCountByOne();
						customerInventory.addToShoppingCart(selectItem);
						Date date = new Date();
				
						log.writeFile(String.format("%s %-18s %s %s", formatter.format(date), nameKeyPair, output, nextOutput));
					}
				}
			}
			// If the user selects 3, generate change
			else if (selection.equals("3")) {
				double preChangeMoney = money.getCurrentMoney();
				floatingDecimalString = String.format("%.2f", preChangeMoney);
				output = "$" + floatingDecimalString;
				System.out.println(money.getChange());
				Date date = new Date();
//				log.writeFile(formatter.format(date) + " GIVE CHANGE $" + preChangeMoney + " $0.00");
				log.writeFile(String.format("%s %-18s %s %s", formatter.format(date) , "GIVE CHANGE:" , output , "$0.00"));
				for (Item item : customerInventory.getShoppingCart()) {
					System.out.println(item.getSound());
				}

				salesPrintOut.updateSalesFile(customerInventory.getNumberItemsSold(), customerInventory.getTotalAmountSpent());

				inSubMenu = false;

			}
			else {
				System.out.println("Invalid Selection! Please choose another number.");

			}
			System.out.println();
		}
		System.out.flush();
		
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
}

	

