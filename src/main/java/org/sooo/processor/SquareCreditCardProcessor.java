package org.sooo.processor;

import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public class SquareCreditCardProcessor implements CreditCardProcessor {

	public ChargeResult charge(CreditCard creditCard, int amount) {
		return new ChargeResult(false, "This is a square credit card.");
	}

}
