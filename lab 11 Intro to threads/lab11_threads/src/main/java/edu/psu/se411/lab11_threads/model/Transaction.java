package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	private boolean synchronizedMode;

	public Transaction(CryptoAccount sender, CryptoAccount receiver, Double amount) {
		this("tx", sender, receiver, amount, false);
	}

	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount, boolean synchronizedMode) {
		if (id == null || sender == null || receiver == null || amount == null || amount < 0)
			throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.synchronizedMode = synchronizedMode;
	}

	@Override
	public void run() {
		try {
			if (synchronizedMode) {
				executeSynchronized(this);
			} else {
				execute();
			}
		} catch (InsufficientBalanceException e) {
			System.err.println(id + ": " + e.getMessage());
		}
	}

	// Part 1: no synchronization -> concurrent transactions corrupt the balances
	public void execute() throws InsufficientBalanceException {
		sender.retrieveFromBalance(amount);
		receiver.addToBalance(amount);
	}

	// Part 2: "synchronized" on ONE method. It is static, so every transaction
	// locks the same monitor (Transaction.class) and only one transfer runs at a time.
	// (A non-static synchronized execute() would NOT work: each thread has its own Transaction object.)
	public static synchronized void executeSynchronized(Transaction t) throws InsufficientBalanceException {
		t.execute();
	}

	public CryptoAccount getSender() {
		return sender;
	}
	public void setSender(CryptoAccount sender) {
		this.sender = sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public void setReceiver(CryptoAccount receiver) {
		this.receiver = receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
}
