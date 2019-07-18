package com.techelevator.items;

public interface Item {
	
	public String getKey();
	public String getName();
	public String getSound();
	public double getPrice();
	public String toString();
	public String getType();
	public int getCount();
	public void reduceCountByOne();
	
}
