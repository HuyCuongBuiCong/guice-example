package org.sooo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sooo.model.ChargeResult;

public class InMemoryTransactionLog implements TransactionLog {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private boolean successLogged = false;

	
	public void logChargeResult(ChargeResult chargeResult) {
		successLogged = chargeResult.wasSuccessful();
		logger.info("logChargeResult() from InMemoryTransactionLog is called");
	}


	public void logConnectException(Throwable t) {
		logger.info("logChargeResult(Throwable) from is called");
	}

	public boolean wasSuccessLogged() {
		return successLogged;
	}

}
