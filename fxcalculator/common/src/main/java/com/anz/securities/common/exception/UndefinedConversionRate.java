package com.anz.securities.common.exception;

public class UndefinedConversionRate  extends Exception {
	private static final long serialVersionUID = 1L;
	public UndefinedConversionRate(String errorMessage ) {
		super(errorMessage);
	}

}