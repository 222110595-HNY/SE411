package edu.psu.se411.lab09_inventory.model;

public abstract class Item {
	private String id;
	private String name;
	
	public Item(String id, String name) {
		super();
		if(id == null || name == null) throw new IllegalArgumentException("id and name cannot be null");
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("%s [id: %s, name: %s]", getClass().getSimpleName(), getId(), getName());
	}
}
