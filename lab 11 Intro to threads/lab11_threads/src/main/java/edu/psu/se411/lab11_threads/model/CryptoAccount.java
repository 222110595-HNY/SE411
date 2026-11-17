package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount {
	private String id;
	private Double balance;

	public CryptoAccount(String id, Double balance) {
		if (balance < 0) throw new IllegalArgumentException("Account balance cannot be negative");
		this.id = id;
		this.balance = balance;
	}

	// NOT thread safe on purpose: read-modify-write of balance is not atomic.
	// Thread.yield() widens the race window so the double spending problem shows up reliably.
	public void addToBalance(Double amount) {
		if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
		Double current = balance;
		Thread.yield();
		balance = current + amount;
	}

	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
		Double current = balance;
		if (current < amount) throw new InsufficientBalanceException("Insufficient balance in account " + id);
		Thread.yield();
		balance = current - amount;
	}

	public String getId() {
		return id;
	}
	public Double getBalance() {
		return balance;
	}

}
