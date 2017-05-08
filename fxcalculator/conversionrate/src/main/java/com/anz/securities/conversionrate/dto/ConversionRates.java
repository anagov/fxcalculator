package com.anz.securities.conversionrate.dto;

import java.util.ArrayList;
import java.util.List;

public class ConversionRates {

	private List<ConversionRate> conversionRateList;

	public ConversionRates() {
		conversionRateList = new ArrayList<ConversionRate>();
	}

	public List<ConversionRate> getConversionRateList() {
		return conversionRateList;
	}

	public void setConversionRateList(List<ConversionRate> conversionRateList) {
		this.conversionRateList = conversionRateList;
	}

	public void addConversionRate(ConversionRate rate) {
		conversionRateList.add(rate);
	}
}
