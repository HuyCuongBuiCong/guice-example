package org.sooo.provider;

import org.sooo.log.InMemoryTransactionLog;
import org.sooo.log.TransactionLog;

import com.google.inject.Provider;

public class InMemoryTransactionLogProvider implements Provider<TransactionLog> {

	@Override
	public TransactionLog get() {
		InMemoryTransactionLog transactionLog = new InMemoryTransactionLog();
	    transactionLog.setJdbcUrl("myURl");
	    transactionLog.setThreadPoolSize(10);
	    return transactionLog;
	  }
	}