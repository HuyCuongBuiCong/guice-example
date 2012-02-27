package org.sooo.model;

public class CreditCard {

	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;

	public CreditCard(String cardNumber, int expiryMonth, int expiryYear) {
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}
}
