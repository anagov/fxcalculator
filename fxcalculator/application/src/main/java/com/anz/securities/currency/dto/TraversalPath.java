package com.anz.securities.currency.dto;

/**
 * 
 * @author xanakat
 *
 */
public class TraversalPath {
	private String fromCurrency;
	private String toCurrency;

	public TraversalPath(final String from, final String to) {
		this.fromCurrency = from;
		this.toCurrency = to;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

}
