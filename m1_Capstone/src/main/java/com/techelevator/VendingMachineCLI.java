package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private Menu menu;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		while(true) {

			menu.mainMenu();
		
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	
}
