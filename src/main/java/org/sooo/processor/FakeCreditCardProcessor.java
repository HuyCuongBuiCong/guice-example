package org.sooo.processor;

import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public class FakeCreditCardProcessor implements CreditCardProcessor {

	private CreditCard cardOfOnlyCharge;
	private int amountOfOnlyCharge;

	@Override
	public ChargeResult charge(CreditCard creditCard, int amount) {
		cardOfOnlyCharge = creditCard;
		amountOfOnlyCharge = amount;
		return new ChargeResult(true, "This is a fake credit card.");
	}

	public CreditCard getCardOfOnlyCharge() {
		return cardOfOnlyCharge;
	}

	public int getAmountOfOnlyCharge() {
		return amountOfOnlyCharge;
	}

}
