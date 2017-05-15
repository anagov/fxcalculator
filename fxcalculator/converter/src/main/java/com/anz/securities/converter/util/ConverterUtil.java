package com.anz.securities.converter.util;

import java.util.Collections;

import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.CurrencyNotSupportedException;
import com.anz.securities.common.exception.UndefinedConversionRate;
import com.anz.securities.entities.apii.ConversionRate;
import com.anz.securities.entities.dto.CurrencyConverter;

public class ConverterUtil {

	public static boolean isCurrencySupported(final String currencyName) throws CurrencyNotSupportedException {
		return false;
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @return
	 * @throws UndefinedConversionRate
	 */
	public ConversionRate getConversionRate(final CurrencyConverter converter, final String src, final String dest) throws UndefinedConversionRate {
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
