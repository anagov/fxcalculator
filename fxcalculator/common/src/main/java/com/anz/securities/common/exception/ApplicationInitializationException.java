package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class ApplicationInitializationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ApplicationInitializationException(final String errMessage) {
		super(errMessage);
	}

}
