package org.sooo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.module.TestBillingModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBillingServiceTest {
	private final PizzaOrder order = new PizzaOrder(100);
	private final CreditCard creditCard = new CreditCard("1234", 11, 2010);

	@Test
	public void testSuccessfulCharge() {
		Injector injector = Guice.createInjector(new TestBillingModule());
		BillingService billingService = injector
				.getInstance(BillingService.class);

		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
		// NOTE: cannot access processor and transactionLog in
		// GuiceBillingService.
	}
}
