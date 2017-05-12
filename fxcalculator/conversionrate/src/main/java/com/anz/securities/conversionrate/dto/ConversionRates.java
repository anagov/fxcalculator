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
	private static final String CONV_DIRECT = "D";
	private static final String CONV_INVERT = "INV";

	private List<ConversionRate> conversionRateList;

	public ConversionRates() {
		conversionRateList = new ArrayList<ConversionRate>();
	}

	public List<ConversionRate> getConversionRateList() {
		return conversionRateList;
	}

	public void setConversionRateList(final List<ConversionRate> conversionRateList) {
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
	public ConversionRate getConversionRate(final String src, final String dest ) throws UndefinedConversionRate {
		String converstionType = CONV_DIRECT;
		Collections.sort(conversionRateList);
		int index = Collections.binarySearch(conversionRateList, new ConversionRate(src, dest));
		if ( index <=0 ) {
			converstionType = CONV_INVERT;
			index = Collections.binarySearch(conversionRateList, new ConversionRate(dest, src));
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
