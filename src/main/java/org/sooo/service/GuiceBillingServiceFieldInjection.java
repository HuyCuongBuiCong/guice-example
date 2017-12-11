package org.sooo.service;

import org.sooo.exception.UnreachableException;
import org.sooo.log.TransactionLog;
import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.processor.CreditCardProcessor;

import com.google.inject.Inject;

public class GuiceBillingServiceFieldInjection implements BillingService {

	@Inject
	private  CreditCardProcessor processor;
	
	@Inject
	private  TransactionLog transactionLog;

	public GuiceBillingServiceFieldInjection(CreditCardProcessor processor,
			TransactionLog transactionLog) {
		this.processor = processor;
		this.transactionLog = transactionLog;
	}
	
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


	public void setProcessor(CreditCardProcessor processor) {
		this.processor = processor;
	}

	public void setTransactionLog(TransactionLog transactionLog) {
		this.transactionLog = transactionLog;
	}	
	
	
}
