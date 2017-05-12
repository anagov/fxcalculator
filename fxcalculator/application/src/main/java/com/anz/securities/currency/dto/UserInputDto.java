package com.anz.securities.currency.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xanakat
 *
 */
public class UserInputDto {
	private String sourceCurrency;
	private String destinationCurrency;
	private String conversionAmount;
	private double convertedAmount;
	private int expectedDecimal;
	public int getExpectedDecimal() {
		return expectedDecimal;
	}
	public void setExpectedDecimal(int expectedDecimal) {
		this.expectedDecimal = expectedDecimal;
	}

	private Map<String, Double> conversionSteps = new HashMap<String, Double>();
	
	private List<TraversalPath> traversal = new ArrayList<TraversalPath>();
	
	public List<TraversalPath> getTraversal() {
		return traversal;
	}
	public void setTraversal(final List<TraversalPath> traversal) {
		this.traversal = traversal;
	}
	
	public void addTraversal(final TraversalPath path) {
		traversal.add(path);
	}
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(final String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	public void setDestinationCurrency(final String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}
	public String getConversionAmount() {
		return conversionAmount;
	}
	public void setConversionAmount(final String conversionAmount) {
		this.conversionAmount = conversionAmount;
	}
	public double getConvertedAmount() {
		return convertedAmount;
	}
	public void setConvertedAmount(final double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
	public Map<String, Double> getConversionSteps() {
		return conversionSteps;
	}
	public void setConversionSteps(final Map<String, Double> conversionSteps) {
		this.conversionSteps = conversionSteps;
	}

	public void addtConversionStep(final String str, final Double convrtedAmt) {
		this.conversionSteps.put(str, convrtedAmt);
	}
}
