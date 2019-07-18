package com.techelevator.process;

public class Money {

	private double currentMoney;

	public double getCurrentMoney() {
		return currentMoney;
	}
	

	public void setCurrentMoney(double money, boolean isSubtract) {
		// To avoid precision errors associated with double, the value is converted into cents 
		// before performing arithmetic
		money *= 100;
		int centAmount = (int) money;
		
		int currentAmountInCents = (int) (currentMoney * 100);
		
		if (isSubtract) {
			currentAmountInCents -= centAmount;
			
		}
		else {
			currentAmountInCents += centAmount;
		}
		currentMoney =  (double) currentAmountInCents / 100;
	}
	
	public boolean getNotEnoughMoney(double price) {
		if (price > currentMoney) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String getChange() {
		
		int change = (int)(Math.ceil(currentMoney * 100));

		// Determines maximum number of quarters possible from change
	    int quarters = Math.round((int) change / 25);
	    change = change % 25;
	    
	    // Determines maximum number of dimes possible from change, after quarters
	    int dimes = Math.round((int) change / 10);
	    change = change % 10;
	    
	    // Determines maximum number of dimes possible from change, after quarters and nickels
	    int nickels = Math.round((int) change / 5);
	    change = change % 5;
//	    int pennies = Math.round((int) change / 1);
	    // Resets change for next purchase
	    currentMoney = 0;
		
	    return quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels";
	}
	
}
