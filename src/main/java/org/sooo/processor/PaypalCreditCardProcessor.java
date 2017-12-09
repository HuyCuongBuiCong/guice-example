package org.sooo.processor;

import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public class PaypalCreditCardProcessor implements CreditCardProcessor {

	public ChargeResult charge(CreditCard creditCard, int amount) {
		return new ChargeResult(true, "");
	}

}
