package com.techelevator.process;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.techelevator.view.Menu;

public class VendingMachineLogWriter {
	
	private Menu menu;

	public void writeFile(String logEntry) {
		
		try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
				
				writer.println(logEntry);
			
		} catch (Exception e){
			menu.displayMessage("ERROR - File not found!");
		}
		
	}

}
