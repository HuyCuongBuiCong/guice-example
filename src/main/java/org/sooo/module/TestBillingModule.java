package org.sooo.module;

import org.sooo.log.InMemoryTransactionLog;
import org.sooo.log.TransactionLog;
import org.sooo.processor.CreditCardProcessor;
import org.sooo.processor.FakeCreditCardProcessor;
import org.sooo.service.BillingService;
import org.sooo.service.GuiceBillingService;

import com.google.inject.AbstractModule;

public class TestBillingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransactionLog.class).to(InMemoryTransactionLog.class);
		bind(CreditCardProcessor.class).to(FakeCreditCardProcessor.class);
		bind(BillingService.class).to(GuiceBillingService.class);
	}

}
