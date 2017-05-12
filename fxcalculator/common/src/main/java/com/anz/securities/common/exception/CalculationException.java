package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class CalculationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CalculationException(final String errMessage) {
		super( errMessage );
	}
}
