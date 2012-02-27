package org.sooo.processor;

import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public class PaypalCreditCardProcessor implements CreditCardProcessor {

	@Override
	public ChargeResult charge(CreditCard creditCard, Integer amount) {
		return new ChargeResult(true, "decline_message");
	}

}
