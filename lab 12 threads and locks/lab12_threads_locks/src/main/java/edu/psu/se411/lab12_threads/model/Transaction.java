package edu.psu.se411.lab12_threads.model;

import java.util.concurrent.atomic.AtomicInteger;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {
	// Counted instead of printed: printing up to a million messages would distort the timings
	public static final AtomicInteger rejected = new AtomicInteger();

	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;

	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		if (id == null || sender == null || receiver == null || amount == null || amount < 0)
			throw new IllegalArgumentException();
		if (sender == receiver)
			throw new IllegalArgumentException("Cannot make a transaction where the sender and the receiver are the same account");
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	@Override
	public void run() {
		try {
			execute();
		} catch (InsufficientBalanceException e) {
			rejected.incrementAndGet();
		}
	}

	/*
	 * What could go wrong with locks: if we simply lock sender then receiver,
	 * T1 (A -> B) locks A and waits for B while T2 (B -> A) locks B and waits for A.
	 * Both wait forever: DEADLOCK.
	 *
	 * Solution (resource ordering): accounts have a total order (their id) and every
	 * transaction always locks the smaller account first, so a cycle of waits is impossible.
	 */
	public void execute() throws InsufficientBalanceException {
		CryptoAccount first = sender.compareTo(receiver) < 0 ? sender : receiver;
		CryptoAccount second = first == sender ? receiver : sender;

		first.getLock().lock();
		try {
			second.getLock().lock();
			try {
				sender.retrieveFromBalance(amount);
				receiver.addToBalance(amount);
			} finally {
				second.getLock().unlock();
			}
		} finally {
			first.getLock().unlock();
		}
	}

	public CryptoAccount getSender() {
		return sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public String getId() {
		return id;
	}

}
