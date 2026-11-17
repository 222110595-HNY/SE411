package edu.psu.se411.lab11_threads;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Integer nbAccounts = 10;
	public static Double initialBalance = 1000.0;
	public static Integer nbTransactions = 10000;

	public static void main(String[] args) {
		logger.info("Application is starting...");

		System.out.println("=== Part 1: without synchronization ===");
		simulate(false);

		System.out.println("\n=== Part 2: with synchronized ===");
		simulate(true);

		logger.info("Application is closing...");
	}

	private static void simulate(boolean synchronizedMode) {
		// 1- Create ten accounts with 1000 riyals each (10000 riyals in the system)
		List<CryptoAccount> accounts = new ArrayList<>();
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts.add(new CryptoAccount("acc_" + i, initialBalance));
		}
		System.out.printf("Initial total money in the system: %.2f%n", computeTotalMoneyInSystem(accounts));

		// 2- Ten thousand threads, each executing one random transaction
		Thread[] threads = new Thread[nbTransactions];
		for (Integer i = 0; i < nbTransactions; i++) {
			CryptoAccount randomA = accounts.get((int) (Math.random() * 10));
			CryptoAccount randomB = accounts.get((int) (Math.random() * 10));
			Double randomAmount = (double) (Math.random() * 10 + 1);
			Transaction t = new Transaction("tx_" + i, randomA, randomB, randomAmount, synchronizedMode);
			threads[i] = new Thread(t);
			threads[i].start();
		}

		// 3- Wait for all transactions to terminate
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				logger.error("Interrupted while waiting for transactions", e);
				Thread.currentThread().interrupt();
			}
		}

		System.out.printf("Final total money in the system: %.2f%n", computeTotalMoneyInSystem(accounts));
	}

	public static Double computeTotalMoneyInSystem(List<CryptoAccount> accounts) {
		Double total = 0.0;
		for (CryptoAccount account : accounts) {
			total += account.getBalance();
		}
		return total;
	}

}
