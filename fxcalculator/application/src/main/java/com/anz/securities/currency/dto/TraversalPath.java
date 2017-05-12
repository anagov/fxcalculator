package com.anz.securities.currency.dto;

public class TraversalPath {
	private String fromCurrency;
	private String toCurrency;

	public TraversalPath(String from, String to) {
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
