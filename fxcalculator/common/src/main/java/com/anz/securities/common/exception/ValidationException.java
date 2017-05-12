package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class ValidationException extends FxCalculatorException {
	
	private static final long serialVersionUID = 1L;
	
	public ValidationException(final String errorMessage ) {
		super(errorMessage);
	}

}
