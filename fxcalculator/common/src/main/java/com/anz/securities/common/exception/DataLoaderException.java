package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class DataLoaderException extends ApplicationInitializationException {
	private static final long serialVersionUID = 1L;
	
	public DataLoaderException( final String errorMsg) {
		super(errorMsg);
	}

}