package edu.spu.se411.lab06_logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;
import edu.spu.se411.lab06_logging.model.WalletAccount;

public class App {
	
	static Logger logger = LoggerFactory.getLogger(App.class);
	

	public static void main(String[] args) {
		
		logger.info("application is starting...");
		
		WalletAccount account = new WalletAccount(1000);
		logger.debug("wallet account created");
        try {
            account.withdraw(1500);
            logger.debug("withdrawal");
        } catch (InsufficientFundsException e) {
           logger.warn("Exception caught: {}", e.getMessage(), e);
        }

        try {
            account.deposit(-100);
            logger.debug("deposit");
        } catch (IllegalArgumentException e) {
           logger.warn("Exception caught: {}", e.getMessage(), e);
        }
        
        logger.info("application ended");
	}

}
