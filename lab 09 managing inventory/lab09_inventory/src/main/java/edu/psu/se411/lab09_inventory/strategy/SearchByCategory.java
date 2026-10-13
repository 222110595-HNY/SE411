package edu.psu.se411.lab09_inventory.strategy;

import edu.psu.se411.lab09_inventory.model.ElectronicDevice;
import edu.psu.se411.lab09_inventory.model.Item;

public class SearchByCategory implements SearchStrategy<Item> {
	private String category;
	
	public SearchByCategory(String category) {
		this.category = category;
	}
	
	@Override
	public boolean matches(Item item) {
		if(item instanceof ElectronicDevice) {
			ElectronicDevice device = (ElectronicDevice) item;
			return device.getCategory().equals(category);
		}
		return false;
	}
}
