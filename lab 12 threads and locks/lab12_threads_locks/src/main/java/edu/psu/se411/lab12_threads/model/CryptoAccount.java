package edu.psu.se411.lab12_threads.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class CryptoAccount implements Comparable<CryptoAccount> {
	private final int id;
	private Double balance;
	// "synchronized" was replaced by an explicit lock, acquired by Transaction
	private final Lock lock = new ReentrantLock();

	public CryptoAccount(int id, Double balance) {
		if (balance < 0) throw new IllegalArgumentException("Account balance cannot be negative");
		this.id = id;
		this.balance = balance;
	}

	// Caller must hold this account's lock
	public void addToBalance(Double amount) {
		if (amount < 0) throw new IllegalArgumentException("Amount added to balance cannot be negative");
		balance += amount;
	}

	// Caller must hold this account's lock
	public void retrieveFromBalance(Double amount) throws InsufficientBalanceException {
		if (amount > balance) throw new InsufficientBalanceException("acc_" + id);
		balance -= amount;
	}

	public int getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

	public Lock getLock() {
		return lock;
	}

	// Total order on accounts (by id), used for resource ordering
	@Override
	public int compareTo(CryptoAccount other) {
		return Integer.compare(this.id, other.id);
	}

}
