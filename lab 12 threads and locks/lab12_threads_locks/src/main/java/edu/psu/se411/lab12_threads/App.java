package edu.psu.se411.lab12_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab12_threads.model.CryptoAccount;
import edu.psu.se411.lab12_threads.model.Transaction;

/**
 * Fixed thread pool version.
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Double initialBalance = 10000.0;
	public static Integer nbTransactions = 1_000_000;

	public static void main(String[] args) {
		logger.info("Application is starting...");
		int nbThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("Fixed thread pool (" + nbThreads + " threads)");
		simulate(Executors.newFixedThreadPool(nbThreads), 1000, nbTransactions);
		simulate(Executors.newFixedThreadPool(nbThreads), 10, nbTransactions);
		logger.info("Application is closing...");
	}

	public static void simulate(ExecutorService executor, int nbAccounts, int nbTransactions) {
		Transaction.rejected.set(0);

		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (int i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount(i, initialBalance);
		}
		System.out.printf("%n%d accounts, %d transactions%n", nbAccounts, nbTransactions);
		System.out.printf("Initial money volume in system: %.2f%n", sumTotalMoneyInAccounts(accounts));

		long startTime = System.currentTimeMillis();

		// 2- choose sender and receiver accounts randomly
		for (int i = 0; i < nbTransactions; i++) {
			int senderIndex = (int) (Math.random() * nbAccounts);
			int receiverIndex = (int) (Math.random() * nbAccounts);
			// make sure indexes are different
			if (senderIndex == receiverIndex) {
				senderIndex = (senderIndex + 1) % nbAccounts;
			}

			// 3- execute transaction
			Double amount = Math.random() * 100;
			executor.execute(new Transaction("tx_" + i, accounts[senderIndex], accounts[receiverIndex], amount));
		}

		// 4- wait for all transactions to complete before verifying total money in system again
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		long endTime = System.currentTimeMillis();

		// 5- check total money in system after all transactions
		System.out.printf("Final money volume in system: %.2f%n", sumTotalMoneyInAccounts(accounts));
		System.out.println("Rejected (insufficient balance): " + Transaction.rejected.get());
		System.out.println("Total time: " + (endTime - startTime) + " milliseconds");
	}

	private static Double sumTotalMoneyInAccounts(CryptoAccount[] accounts) {
		Double total = 0.0;
		for (CryptoAccount c : accounts) {
			total += c.getBalance();
		}
		return total;
	}

}
