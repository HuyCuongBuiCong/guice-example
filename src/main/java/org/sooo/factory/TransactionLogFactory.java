package org.sooo.factory;

import org.sooo.log.InMemoryTransactionLog;
import org.sooo.log.TransactionLog;

public class TransactionLogFactory {

	private static TransactionLog instance;

	public static void setInstance(TransactionLog transactionLog) {
		instance = transactionLog;
	}

	public static TransactionLog getInstance() {
		if (instance == null)
			return new InMemoryTransactionLog();

		return instance;
	}
}
