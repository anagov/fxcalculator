package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class CurrencyNotSupportedException extends CurrencyConversionException {

	private static final long serialVersionUID = 1L;
	
	public CurrencyNotSupportedException(final String errorMessage) {
		super(errorMessage);
	}
}

