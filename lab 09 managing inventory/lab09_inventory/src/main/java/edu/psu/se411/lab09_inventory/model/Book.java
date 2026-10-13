package edu.psu.se411.lab09_inventory.model;

public class Book extends Item {
	private String author;
	
	public Book(String id, String name, String author) {
		super(id, name);
		if(author == null) throw new IllegalArgumentException("author cannot be null");
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {
		return String.format("%s, author: %s", super.toString(), getAuthor());
	}
}
