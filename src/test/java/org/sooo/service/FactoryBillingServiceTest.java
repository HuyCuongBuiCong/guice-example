package org.sooo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sooo.factory.CreditCardProcessorFactory;
import org.sooo.factory.TransactionLogFactory;
import org.sooo.log.InMemoryTransactionLog;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.processor.FakeCreditCardProcessor;

public class FactoryBillingServiceTest {

	private final PizzaOrder order = new PizzaOrder(100);
	private final CreditCard creditCard = new CreditCard("1234", 11, 2010);

	InMemoryTransactionLog transactionLog = new InMemoryTransactionLog();
	FakeCreditCardProcessor creditCardProcessor = new FakeCreditCardProcessor();

	@Before
	public void setUp() {
		TransactionLogFactory.setInstance(transactionLog);
		CreditCardProcessorFactory.setInstance(creditCardProcessor);
	}

	@After
	public void tearDown() {
		TransactionLogFactory.setInstance(null);
		CreditCardProcessorFactory.setInstance(null);
	}

	@Test
	public void testSuccessfulCharge() {
		FactoryBillingService billingService = new FactoryBillingService();
		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
		assertEquals(creditCard, creditCardProcessor.getCardOfOnlyCharge());
		assertEquals(100, creditCardProcessor.getAmountOfOnlyCharge());
		assertTrue(transactionLog.wasSuccessLogged());
	}
}
