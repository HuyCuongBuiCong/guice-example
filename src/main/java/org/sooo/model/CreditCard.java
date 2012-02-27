package org.sooo.model;

public class CreditCard {

	private String cardNumber;
	private Integer expiryMonth;
	private Integer expiryYear;

	public CreditCard(String cardNumber, Integer expiryMonth, Integer expiryYear) {
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Integer getExpiryMonth() {
		return expiryMonth;
	}

	public Integer getExpiryYear() {
		return expiryYear;
	}
}
