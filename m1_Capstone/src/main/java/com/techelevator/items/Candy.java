package com.techelevator.items;

public class Candy implements Item {

	private String key;
	private String name;
	private String sound = "Munch Munch, Yum!";
	private double price;
	private String type = "Candy";
	private int count;
	
	public Candy(String key, String name, double item, int count) {
		this.key = key;
		this.name = name;
		this.price = item;
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "Candy";
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