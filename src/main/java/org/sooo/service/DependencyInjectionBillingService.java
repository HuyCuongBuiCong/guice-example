package org.sooo.service;

import org.sooo.exception.UnreachableException;
import org.sooo.log.TransactionLog;
import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.processor.CreditCardProcessor;

public class DependencyInjectionBillingService implements BillingService {

	private final CreditCardProcessor processor;
	private final TransactionLog transactionLog;

	public DependencyInjectionBillingService(CreditCardProcessor processor,
			TransactionLog transactionLog) {
		this.processor = processor;
		this.transactionLog = transactionLog;
	}

	@Override
	public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
		try {
			ChargeResult result = processor.charge(creditCard,
					order.getAmount());
			transactionLog.logChargeResult(result);

			return result.wasSuccessful() ? Receipt.forSuccessfulCharge(order
					.getAmount()) : Receipt.forDeclinedCharge(result
					.getDeclineMessage());
		} catch (UnreachableException e) {
			transactionLog.logConnectException(e);
			return Receipt.forSystemFailure(e.getMessage());
		}
	}

}
