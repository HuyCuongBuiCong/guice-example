package org.sooo.payment;

public class SimpleLocaleInfo implements LocaleInfo {

	@Override
	public String getMoneySign() {
		return "$";
	}

	@Override
	public boolean isMoneySignBefore() {
		return false;
	}

}
