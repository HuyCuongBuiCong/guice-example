package org.sooo.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public class FakeCreditCardProcessor implements CreditCardProcessor {

	private CreditCard cardOfOnlyCharge;
	private int amountOfOnlyCharge;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ChargeResult charge(CreditCard creditCard, int amount) {
		cardOfOnlyCharge = creditCard;
		amountOfOnlyCharge = amount;
		logger.info("charge()from FakeCreditCardProcessor is called");
		return new ChargeResult(true, "This is a fake credit card.");
	}

	public CreditCard getCardOfOnlyCharge() {
		return cardOfOnlyCharge;
	}

	public int getAmountOfOnlyCharge() {
		return amountOfOnlyCharge;
	}

}
