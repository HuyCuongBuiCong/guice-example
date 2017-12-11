package org.sooo.paymentfactory;

import java.util.Date;

import org.sooo.payment.Payment;

public interface PaymentFactory {
	public Payment create(Date startDate, int amount);
}