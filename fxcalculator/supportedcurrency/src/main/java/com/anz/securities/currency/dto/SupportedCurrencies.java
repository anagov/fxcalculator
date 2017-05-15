package com.anz.securities.currency.dto;

import java.util.Map;

import com.anz.securities.common.exception.CurrencyNotSupportedException;

/**
 * Type to represent list of currencies and their expected decimal value
 * 
 * @author Anand Katti
 *
 */
public class SupportedCurrencies {
	private Map<String, String> supCurrency;

	public SupportedCurrencies(final Map<String, String> supCur) {
		supCurrency = supCur;
	}

	/**
	 * returns true if the currency is supported
	 * 
	 * @param currency
	 * @return boolean
	 */
	public boolean isSupported(final String currency) {
		return supCurrency.keySet().contains(currency);
	}

	/**
	 * returns the decimal support for the currency
	 * 
	 * @param currency
	 * @return decimalSupport
	 * @throws CurrencyNotSupportedException
	 */
	public int getDecimalPlaceSupport(final String currency) throws CurrencyNotSupportedException {

		if (!supCurrency.keySet().contains(currency)) {
			throw new CurrencyNotSupportedException("currency not supported");
		}
		return Integer.parseInt(supCurrency.get(currency));
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		if (supCurrency != null) {
			for (Map.Entry<String, String> entry : supCurrency.entrySet()) {
				strBuilder.append("Curreny Name - ");
				strBuilder.append(entry.getKey());
				strBuilder.append("- Supported decimal - ");
				strBuilder.append(entry.getValue());
				strBuilder.append("\n");
			}
		}
		return strBuilder.toString();

	}
}
