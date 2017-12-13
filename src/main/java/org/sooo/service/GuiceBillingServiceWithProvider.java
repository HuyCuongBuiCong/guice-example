package org.sooo.service;

import org.sooo.exception.UnreachableException;
import org.sooo.log.TransactionLog;
import org.sooo.model.ChargeResult;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.processor.CreditCardProcessor;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GuiceBillingServiceWithProvider implements BillingService {

	private final Provider<CreditCardProcessor> processorProvider;
	private final Provider<TransactionLog> transactionLogProvider;

	@Inject
	private GuiceBillingServiceWithProvider(Provider<CreditCardProcessor> processorProvider,
			Provider<TransactionLog> transactionProvider) {
		this.processorProvider = processorProvider;
		this.transactionLogProvider = transactionProvider;
	}

	@Override
	public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {
		try {
			ChargeResult result = processorProvider.get().charge(creditCard,
					order.getAmount());
			transactionLogProvider.get().logChargeResult(result);

			return result.wasSuccessful() ? Receipt.forSuccessfulCharge(order
					.getAmount()) : Receipt.forDeclinedCharge(result
					.getDeclineMessage());
		} catch (UnreachableException e) {
			transactionLogProvider.get().logConnectException(e);
			return Receipt.forSystemFailure(e.getMessage());
		}
	}

}
