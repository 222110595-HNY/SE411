package edu.psu.se411.lab09_inventory.model;

import java.util.ArrayList;
import java.util.List;

import edu.psu.se411.lab09_inventory.strategy.SearchStrategy;

public class Inventory<T> {
	private List<T> items = new ArrayList<>();
	
	public void addItem(T item) {
		if(item == null) throw new IllegalArgumentException("item cannot be null");
		items.add(item);
	}
	
	public boolean removeItem(T item) {
		return items.remove(item);
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public List<T> findItems(SearchStrategy<T> strategy) {
		List<T> result = new ArrayList<>();
		for(T item : items) {
			if(strategy.matches(item)) {
				result.add(item);
			}
		}
		return result;
	}
}
