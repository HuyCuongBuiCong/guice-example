package org.sooo.model;

public class Receipt {

	public static Receipt forSuccessfulCharge(Integer amount) {
		return new Receipt();
	}

	public static Receipt forDeclinedCharge(String declineMessage) {
		return new Receipt();
	}

	public static Receipt forSystemFailure(String errorMessage) {
		return new Receipt();
	}
}
