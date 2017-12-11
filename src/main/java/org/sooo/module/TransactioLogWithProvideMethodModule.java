package org.sooo.module;

import org.sooo.log.DatabaseTransactionLog;
import org.sooo.log.TransactionLog;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class TransactioLogWithProvideMethodModule extends AbstractModule {

	@Override
	protected void configure() {
		//No need to binding here
	}

	@Provides
	TransactionLog provideTransactionLog() {
		DatabaseTransactionLog transactionLog = new DatabaseTransactionLog();
		transactionLog.setJdbcUrl("jdbc:mysql://localhost/pizza");
		transactionLog.setThreadPoolSize(30);
		return transactionLog;
	}

}
