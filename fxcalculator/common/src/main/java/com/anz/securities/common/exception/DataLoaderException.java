package com.anz.securities.common.exception;

/**
 * Exceptions occurred during the data initialization are captured here
 * 
 * @author Anand Katti
 *
 */
public class DataLoaderException extends ApplicationInitializationException {
	private static final long serialVersionUID = 1L;

	public DataLoaderException(final String errorMsg) {
		super(errorMsg);
	}

}