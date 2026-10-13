package model;

import exceptions.InsufficientFundsException;

public class Wallet {

	private String walletId;
	private double balance;

	public Wallet(String walletId, double balance) {
		this.walletId = walletId;
		this.balance = balance;
	}

	public String getWalletId() {
		return walletId;
	}

	public double getBalance() {
		return balance;
	}

	public void withdrawToBankAccount(Account bankAccount, double amount) throws InsufficientFundsException {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive.");
		}
		if (amount > balance) {
			throw new InsufficientFundsException(
					"Cannot withdraw " + amount + " from wallet " + walletId + ": available balance is only " + balance);
		}

		balance -= amount;
		bankAccount.deposit(amount);
	}

	public void transferTo(Wallet receiver, double amount) throws InsufficientFundsException {
		if (amount <= 0) {
			throw new IllegalArgumentException("Transfer amount must be positive.");
		}
		if (amount > balance) {
			throw new InsufficientFundsException(
					"Cannot transfer " + amount + " from wallet " + walletId + ": available balance is only " + balance);
		}

		balance -= amount;
		receiver.balance += amount;
	}
}
