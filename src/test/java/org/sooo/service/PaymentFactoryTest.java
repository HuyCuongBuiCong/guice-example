package org.sooo.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;
import org.sooo.payment.LocaleInfo;
import org.sooo.payment.Payment;
import org.sooo.payment.RealPayment;
import org.sooo.payment.SimpleLocaleInfo;
import org.sooo.paymentfactory.PaymentFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class PaymentFactoryTest {
	@Test
    public void testFactory() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(LocaleInfo.class).to(SimpleLocaleInfo.class); 
				install(new FactoryModuleBuilder().implement(Payment.class, RealPayment.class)
		                    .build(PaymentFactory.class));
			}
		});
		
		PaymentFactory factory = injector.getInstance(PaymentFactory.class);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.US);
        calendar.set(2011, 4, 24); // Month is 0-based
        Payment payment = factory.create(calendar.getTime(), 50);
        assertEquals("Paid 50$ on 05/24/2011", payment.format());
    }
}
