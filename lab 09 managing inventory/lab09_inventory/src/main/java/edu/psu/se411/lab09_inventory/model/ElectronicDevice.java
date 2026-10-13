package edu.psu.se411.lab09_inventory.model;

public class ElectronicDevice extends Item {
	private String category;
	
	public ElectronicDevice(String id, String name, String category) {
		super(id, name);
		if(category == null) throw new IllegalArgumentException("category cannot be null");
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	@Override
	public String toString() {
		return String.format("%s, category: %s", super.toString(), getCategory());
	}
}
