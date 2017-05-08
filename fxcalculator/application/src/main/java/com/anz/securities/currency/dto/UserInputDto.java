package com.anz.securities.currency.dto;

import java.util.HashMap;
import java.util.Map;

public class UserInputDto {
	private String sourceCurrency;
	private String destinationCurrency;
	private double conversionAmount;
	private double convertedAmount;
	private Map<String, Double> conversionSteps = new HashMap<String, Double>();
	
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}
	public double getConversionAmount() {
		return conversionAmount;
	}
	public void setConversionAmount(double conversionAmount) {
		this.conversionAmount = conversionAmount;
	}
	public double getConvertedAmount() {
		return convertedAmount;
	}
	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	public Map<String, Double> getConversionSteps() {
		return conversionSteps;
	}
	public void setConversionSteps(Map<String, Double> conversionSteps) {
		this.conversionSteps = conversionSteps;
	}


}
