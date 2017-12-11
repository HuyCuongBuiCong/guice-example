package org.sooo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sooo.model.ChargeResult;

public class DatabaseTransactionLog implements TransactionLog {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String JdbcUrl;
	private int threadPoolSize;

	@Override
	public void logChargeResult(ChargeResult chargeResult) {
		this.logger.info("logChargeResult(ChargeResult) called");
	}

	@Override
	public void logjdbcUrlAndThreadpoolSize() {
		this.logger.info("JdbcUrl: " + this.getJdbcUrl());
		this.logger.info("threadPoolSize: " +  this.getThreadPoolSize());
	}

	@Override
	public void logConnectException(Throwable t) {
		this.logger.info("logChargeResult(Throwable) called");
	}

	public String getJdbcUrl() {
		return this.JdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.JdbcUrl = jdbcUrl;
	}

	public int getThreadPoolSize() {
		return this.threadPoolSize;
	}


	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

}
