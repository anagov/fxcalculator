package com.anz.securities.currency.dto;

public class TraversalPath {
	private String fromCurrency;
	private String toCurrency;
	private String conversionType;
	
	public TraversalPath(String from, String to, String convType) {
		this.fromCurrency = from;
		this.toCurrency = to;
		this.conversionType = convType;
	}
	
	public String getFromCurrency() {
		return fromCurrency;
	}


	public String getToCurrency() {
		return toCurrency;
	}

	public String getConversionType() {
		return conversionType;
	}

	


}

