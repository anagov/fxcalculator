package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class UndefinedConversionRate  extends CurrencyConversionException {
	private static final long serialVersionUID = 1L;
	
	public UndefinedConversionRate(final String errorMessage ) {
		super(errorMessage);
	}

}