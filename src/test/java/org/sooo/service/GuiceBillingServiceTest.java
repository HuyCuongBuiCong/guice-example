package org.sooo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sooo.log.InMemoryTransactionLog;
import org.sooo.log.TransactionLog;
import org.sooo.model.CreditCard;
import org.sooo.model.PizzaOrder;
import org.sooo.model.Receipt;
import org.sooo.module.TestBillingModule;
import org.sooo.module.TransactioLogWithProvideMethodModule;
import org.sooo.processor.CreditCardProcessor;
import org.sooo.processor.FakeCreditCardProcessor;
import org.sooo.provider.DatabaseTransactionLogProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceBillingServiceTest {
	private final PizzaOrder order = new PizzaOrder(100);
	private final CreditCard creditCard = new CreditCard("1234", 11, 2010);
	
	@Test
	public void testBasicInjectWithGuice() {
		Injector injector = Guice.createInjector(new TestBillingModule());	
		BillingService billingService = injector.getInstance(BillingService.class);
		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
	}
	
	
	@Test
	public void testBasicInjectWithGuiceFieldInjection() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TransactionLog.class).to(InMemoryTransactionLog.class);
				bind(CreditCardProcessor.class).to(FakeCreditCardProcessor.class);
				bind(BillingService.class).to(GuiceBillingServiceFieldInjection.class);
			}
		});

		BillingService billingService = injector.getInstance(BillingService.class);
		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
	}
	
	@Test
	public void testDatabaseTransactionLogProvideMethod() {
		Injector injector = Guice.createInjector(new TransactioLogWithProvideMethodModule());
		TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
		transactionLog.logjdbcUrlAndThreadpoolSize();
	}
		
	@Test
	public void testDatabaseTransactionLogProvider() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TransactionLog.class).toProvider(DatabaseTransactionLogProvider.class);
			}
		});

		TransactionLog transactionLog = injector.getInstance(TransactionLog.class);
		transactionLog.logjdbcUrlAndThreadpoolSize();
	}
	   
	@Test
	public void testBasicInjectWithGuiceMethodInjection() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TransactionLog.class).to(InMemoryTransactionLog.class);
				bind(CreditCardProcessor.class).to(FakeCreditCardProcessor.class);
				bind(BillingService.class).to(GuiceBillingServiceMethodInjection.class);
			}
		});

		BillingService billingService = injector.getInstance(BillingService.class);
		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
	}  
	
	InMemoryTransactionLog transactionLog = new InMemoryTransactionLog();
	FakeCreditCardProcessor creditCardProcessor = new FakeCreditCardProcessor();

	@Test
	public void testSuccessfulCharge() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(TransactionLog.class).toInstance(transactionLog);
				bind(CreditCardProcessor.class).toInstance(creditCardProcessor);
				bind(BillingService.class).to(GuiceBillingService.class);
			}
		});
		
		BillingService billingService = injector
				.getInstance(BillingService.class);

		Receipt receipt = billingService.chargeOrder(order, creditCard);

		assertTrue(receipt.hasSuccessfulCharge());
		assertEquals(100, receipt.getAmountOfCharge());
		assertEquals(creditCard, creditCardProcessor.getCardOfOnlyCharge());
		assertEquals(100, creditCardProcessor.getAmountOfOnlyCharge());
		assertTrue(transactionLog.wasSuccessLogged());
	}
}
