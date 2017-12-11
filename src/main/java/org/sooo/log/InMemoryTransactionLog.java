package org.sooo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sooo.model.ChargeResult;

public class InMemoryTransactionLog implements TransactionLog {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private boolean successLogged = false;
	private String jdbcUrl;
	private int threadPoolSize;

	@Override
	public void logChargeResult(ChargeResult chargeResult) {
		this.successLogged = chargeResult.wasSuccessful();
		this.logger.info("logChargeResult() from InMemoryTransactionLog is called");

	}

	@Override
	public void logjdbcUrlAndThreadpoolSize() {
		this.logger.info("JdbcUrl: " + this.getJdbcUrl());
		this.logger.info("threadPoolSize: " +  this.getThreadPoolSize());
	}

	@Override
	public void logConnectException(Throwable t) {
		this.logger.info("logChargeResult(Throwable) from is called");
	}

	public boolean wasSuccessLogged() {
		return this.successLogged;
	}


	public String getJdbcUrl() {
		return this.jdbcUrl;
	}


	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}


	public int getThreadPoolSize() {
		return this.threadPoolSize;
	}


	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}


}
