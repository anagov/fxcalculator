package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class CurrencyConversionException extends FxCalculatorException {
	private static final long serialVersionUID = 1L;
	
	public CurrencyConversionException(final String errMessage) {
		super(errMessage);
	}

}
