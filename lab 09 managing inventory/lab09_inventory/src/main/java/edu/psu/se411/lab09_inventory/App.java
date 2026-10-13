package edu.psu.se411.lab09_inventory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab09_inventory.model.Book;
import edu.psu.se411.lab09_inventory.model.ElectronicDevice;
import edu.psu.se411.lab09_inventory.model.Inventory;
import edu.psu.se411.lab09_inventory.model.Item;
import edu.psu.se411.lab09_inventory.strategy.SearchByAuthor;
import edu.psu.se411.lab09_inventory.strategy.SearchByCategory;
import edu.psu.se411.lab09_inventory.strategy.SearchById;
import edu.psu.se411.lab09_inventory.strategy.SearchByName;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");

		try {
			Inventory<Item> inventory = new Inventory<>();
			inventory.addItem(new Book("b_1", "Concurrency in Java", "Brian Goetz"));
			inventory.addItem(new Book("b_2", "Effective Java", "Joshua Bloch"));
			inventory.addItem(new Book("b_3", "Java Puzzlers", "Joshua Bloch"));
			inventory.addItem(new ElectronicDevice("e_1", "Galaxy S24", "Phone"));
			inventory.addItem(new ElectronicDevice("e_2", "ThinkPad X1", "Laptop"));
			inventory.addItem(new ElectronicDevice("e_3", "iPhone 16", "Phone"));

			System.out.println("Search by name:");
			displayItems(inventory.findItems(new SearchByName("Concurrency in Java")));

			System.out.println("Search by id:");
			displayItems(inventory.findItems(new SearchById("e_2")));

			System.out.println("Search by author:");
			displayItems(inventory.findItems(new SearchByAuthor("Joshua Bloch")));

			System.out.println("Search by category:");
			displayItems(inventory.findItems(new SearchByCategory("Phone")));

			System.out.println("Search using a lambda expression:");
			displayItems(inventory.findItems(item -> item.getName().startsWith("Galaxy")));

			Item removed = inventory.findItems(new SearchById("b_3")).get(0);
			inventory.removeItem(removed);

			System.out.println("Inventory content:");
			displayInventory(inventory);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		}

		logger.info("Application is closing...");
	}

	public static void displayInventory(Inventory<?> inventory) {
		for(Object item : inventory.getItems()) {
			System.out.println(item);
		}
	}

	public static void displayItems(List<Item> items) {
		for(Item item : items) {
			System.out.println(item);
		}
	}

}
