package org.sooo.provider;

import org.sooo.log.InMemoryTransactionLog;
import org.sooo.log.TransactionLog;

import com.google.inject.Provider;

public class DatabaseTransactionLogProvider implements Provider<TransactionLog> {

	@Override
	public TransactionLog get() {
		InMemoryTransactionLog transactionLog = new InMemoryTransactionLog();
	    transactionLog.setJdbcUrl("jdbc:mysql://localhost/pizza");
	    transactionLog.setThreadPoolSize(30);
	    return transactionLog;
	  }
	}