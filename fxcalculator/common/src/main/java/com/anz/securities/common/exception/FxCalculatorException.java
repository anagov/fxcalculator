package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class FxCalculatorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FxCalculatorException(final String errMessage) {
		super(errMessage);
	}
}
