package com.anz.securities.common.exception;

/**
 * 
 * @author xanakat
 *
 */
public class RuleNotFoundException extends FxCalculatorException {
	private static final long serialVersionUID = 1L;

	public RuleNotFoundException(final String errMessage) {
		super(errMessage);
	}

}
