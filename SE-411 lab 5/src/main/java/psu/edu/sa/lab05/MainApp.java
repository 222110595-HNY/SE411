package psu.edu.sa.lab05;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAgeException;
import model.Account;
import model.Wallet;

public class MainApp {

	public static void validateAge(int age) throws InvalidAgeException {
		if (age < 18) {
			throw new InvalidAgeException("Invalid age " + age + ": must be at least 18 years old.");
		}
		System.out.println("Age valid message.");
	}

	public static void main(String[] args) {
		try {
			validateAge(20);
			validateAge(15);
		} catch (InvalidAgeException e) {
			System.out.println("Age validation failed: " + e.getMessage());
		}

		Wallet wallet = new Wallet("wallet-1", 100.0);
		Account bankAccount = new Account("acc-1", 0.0);

		try {
			wallet.withdrawToBankAccount(bankAccount, 40.0);
			System.out.println("Withdrew 40.0. Wallet balance: " + wallet.getBalance()
					+ ", bank account balance: " + bankAccount.getBalance());

			wallet.withdrawToBankAccount(bankAccount, 1000.0);
		} catch (InsufficientFundsException e) {
			System.out.println("Withdrawal failed: " + e.getMessage());
		}
	}
}
