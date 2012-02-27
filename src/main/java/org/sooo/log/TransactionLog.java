package org.sooo.log;

import org.sooo.model.ChargeResult;

public interface TransactionLog {

	void logChargeResult(ChargeResult chargeResult);

	void logConnectException(Throwable t);
}
