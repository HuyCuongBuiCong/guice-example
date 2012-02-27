package org.sooo.model;

public class Receipt {

	private boolean success;
	private int amountOfCharge;
	private String declineMessage;

	public Receipt(boolean success, int amountOfCharge, String declineMessage) {
		super();
		this.success = success;
		this.amountOfCharge = amountOfCharge;
		this.declineMessage = declineMessage;
	}

	public static Receipt forSuccessfulCharge(int amount) {
		return new Receipt(true, amount, "");
	}

	public static Receipt forDeclinedCharge(String declineMessage) {
		return new Receipt(false, 0, "Declined");
	}

	public static Receipt forSystemFailure(String errorMessage) {
		return new Receipt(false, 0, "System Failure");
	}

	public boolean hasSuccessfulCharge() {
		return success;
	}

	public int getAmountOfCharge() {
		return amountOfCharge;
	}

	public String getDeclineMessage() {
		return declineMessage;
	}
}
