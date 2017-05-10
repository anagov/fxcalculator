package com.anz.securities.common.exception;

public class CalculationException extends Exception {

	static final long serialVersionUID = 1L;
	
	public CalculationException(String errMessage) {
		super( errMessage );
	}
}
