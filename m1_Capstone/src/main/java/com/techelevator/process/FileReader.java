package com.techelevator.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class FileReader {
	
	private String fileName;
	private Menu menu;
	
	public FileReader(String fileName) {
		this.fileName = fileName;
	}

	public List<String[]> getFileContents() {
		
		List<String[]>vendingMachineItems = new ArrayList<String[]>();
		
		try (Scanner file = new Scanner(new File(fileName))) {
			while (file.hasNextLine()) {
				String[] items = file.nextLine().split("\\|");
				vendingMachineItems.add(items);
			}
			return vendingMachineItems;
		
		} catch (FileNotFoundException e) {
			menu.displayMessage("ERROR - File not found!");
		}
		return null;
	}
	
}
