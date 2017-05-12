package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class LoaderNotSupportedException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public LoaderNotSupportedException( final String errMessage ) {
		super(errMessage);
	}

}
