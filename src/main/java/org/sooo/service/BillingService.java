package org.sooo.service;

import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;

public interface BillingService {

	Receipt chargeOrder(PizzaOrder order, CreditCard creditCard);
}
