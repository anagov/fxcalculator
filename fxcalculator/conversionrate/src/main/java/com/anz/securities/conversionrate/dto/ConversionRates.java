package com.anz.securities.conversionrate.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.anz.securities.common.exception.UndefinedConversionRate;

/**
 * 
 * @author xanakat
 *
 */
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
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @return
	 * @throws UndefinedConversionRate
	 */
	public ConversionRate getConversionRate( String src, String dest ) throws UndefinedConversionRate {
		String converstionType = "D";
		Collections.sort(conversionRateList);
		int index = Collections.binarySearch(conversionRateList, new ConversionRate(src, dest));
		System.out.println("The index is " + index);
		if ( index <=0 ) {
			converstionType = "INV";
			index = Collections.binarySearch(conversionRateList, new ConversionRate(dest, src));
			System.out.println("The index is " + index);
		}
		try {
			ConversionRate rate = conversionRateList.get(index);
			rate.setConversionType(converstionType);
			return rate;
		} catch ( Exception ex ) {
			throw new UndefinedConversionRate("Rate not defined for");
		}
	}
}
