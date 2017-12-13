package org.sooo.module;

import org.sooo.log.DatabaseTransactionLog;
import org.sooo.log.TransactionLog;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class TransactioLogWithProvideMethodModuleAndArgument extends AbstractModule {

	@Override
	protected void configure() {
		 bind(String.class)
	        .annotatedWith(Names.named("jdbcUrl"))
	        .toInstance("jdbc:mysql://localhost/pizza");
		 
		 bind(Integer.class)
	        .annotatedWith(Names.named("poolSize"))
	        .toInstance(30);
	}

	@Provides
	TransactionLog provideTransactionLog(@Named("jdbcUrl") String jdbcUrl,
			@Named("poolSize") Integer poolSize) {
		DatabaseTransactionLog transactionLog = new DatabaseTransactionLog();
		transactionLog.setJdbcUrl("jdbc:mysql://localhost/pizza");
		transactionLog.setThreadPoolSize(30);
		return transactionLog;
	}

}
