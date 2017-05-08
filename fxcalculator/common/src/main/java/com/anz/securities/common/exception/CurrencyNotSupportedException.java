package com.anz.securities.common.exception;

public class CurrencyNotSupportedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CurrencyNotSupportedException(String errorMessage) {
		super(errorMessage);
	}
}

