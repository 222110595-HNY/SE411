package edu.psu.se411.lab11_threads;

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
	public static Double initialBalance = 10000.0;
	public static Integer nbTransactions = 10;

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		// 1- Create accounts
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}
		
		
		Thread[] threads = new Thread[nbTransactions];
		for(Integer i =0; i<nbTransactions;i++) 
		{
			CryptoAccount sender = accounts[(int)(Math.random()*nbAccounts)];
			CryptoAccount receiver = accounts[(int)(Math.random()*nbAccounts)];
			if(sender == receiver) {
				i--;
				continue;
			}
			
			int amount = (int)(Math.random()*100);
			
			Transaction transaction = new Transaction("tx_"+i.toString(), sender, receiver, (double) amount);
			Thread t = new Thread(transaction);
			threads[i] = t;
			t.start();
		}

		for(Integer i =0; i<threads.length;i++) {

			try {
				threads[i].join();

			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

		System.out.println("final total money in the system: " + computeTotalMoneyInSystem(accounts));

		logger.info("Application is closing...");
	}

	public static Double computeTotalMoneyInSystem(CryptoAccount[] accounts) {
		Double total = 0.0;
		for(CryptoAccount account : accounts) {
			total += account.getBalance();
		}
		return total;
	}

}
