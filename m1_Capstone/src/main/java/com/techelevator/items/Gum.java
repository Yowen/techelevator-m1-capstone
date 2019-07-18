package com.techelevator.items;

public class Gum implements Item {

	private String key;
	private String name;
	private String sound = "Chew Chew, Yum!";
	private double price;
	private String type = "Gum";
	private int count;
	
	public Gum(String key, String name, double price, int count) {
		this.key = key;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public String getSound() {
		return sound;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	public void reduceCountByOne() {
		count = count - 1;
	}
	
}
