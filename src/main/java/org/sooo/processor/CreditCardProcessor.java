package org.sooo.processor;

import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;

public interface CreditCardProcessor {

	ChargeResult charge(CreditCard creditCard, Integer amount);
}
