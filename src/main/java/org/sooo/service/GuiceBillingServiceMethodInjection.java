package org.sooo.service;

import org.sooo.exception.UnreachableException;
import org.sooo.log.TransactionLog;
import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.processor.CreditCardProcessor;

import com.google.inject.Inject;

public class GuiceBillingServiceMethodInjection implements BillingService {

	private  CreditCardProcessor processor;
	private  TransactionLog transactionLog;

	public GuiceBillingServiceMethodInjection() {
	}
	
	@Inject
	public void setProcessor( CreditCardProcessor processor) {
		this.processor = processor;
	}
	
	@Inject
	public void setTransactionLog(TransactionLog transactionLog) {
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


	
	
}
