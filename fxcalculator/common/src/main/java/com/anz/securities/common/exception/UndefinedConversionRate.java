package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class UndefinedConversionRate  extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UndefinedConversionRate(final String errorMessage ) {
		super(errorMessage);
	}

}