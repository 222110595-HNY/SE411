package edu.psu.se411.lab09_inventory.strategy;

import edu.psu.se411.lab09_inventory.model.Book;
import edu.psu.se411.lab09_inventory.model.Item;

public class SearchByAuthor implements SearchStrategy<Item> {
	private String author;
	
	public SearchByAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public boolean matches(Item item) {
		if(item instanceof Book) {
			Book book = (Book) item;
			return book.getAuthor().equals(author);
		}
		return false;
	}
}
