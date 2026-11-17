package edu.psu.se411.lab12_threads;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cached thread pool version.
 * Run with: mvn clean package exec:java -Dexec.mainClass=edu.psu.se411.lab12_threads.App2
 */
public class App2 {

	static Logger logger = LoggerFactory.getLogger(App2.class);

	public static void main(String[] args) {
		logger.info("Application is starting...");
		System.out.println("Cached thread pool");
		App.simulate(Executors.newCachedThreadPool(), 1000, App.nbTransactions);
		App.simulate(Executors.newCachedThreadPool(), 10, App.nbTransactions);
		logger.info("Application is closing...");
	}

}
