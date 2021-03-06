package com.anz.securities.conversionrate.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.anz.securities.common.Constants;
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
	public ConversionRate getConversionRate(final String src, final String dest) throws UndefinedConversionRate {
		try {
			String converstionType = Constants.CONV_DIRECT;
			Collections.sort(conversionRateList);
			int index = Collections.binarySearch(conversionRateList, new ConversionRate(src, dest));
			if (index < 0) {
				converstionType = Constants.CONV_INVERT;
				index = Collections.binarySearch(conversionRateList, new ConversionRate(dest, src));
			}

			ConversionRate rate = conversionRateList.get(index);
			rate.setConversionType(converstionType);
			return rate;
		} catch (Exception ex) {
			throw new UndefinedConversionRate("Rate not defined for" + ex.getMessage());
		}
	}
}
