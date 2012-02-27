package org.sooo.model;

public class ChargeResult {

	private boolean success;
	private String declineMessage;

	public ChargeResult(boolean success, String declineMessage) {
		this.success = success;
		this.declineMessage = declineMessage;
	}

	public boolean wasSuccessful() {
		return success;
	}

	public String getDeclineMessage() {
		return declineMessage;
	}
}
