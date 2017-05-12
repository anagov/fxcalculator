package com.anz.securities.currency.dto;

import java.util.Map;

import com.anz.securities.common.exception.CurrencyNotSupportedException;

/**
 * 
 * @author xanakat
 *
 */
public class SupportedCurrencies {
	private Map<String, String> supCurrency;

	public SupportedCurrencies(Map<String, String> supCur) {
		supCurrency = supCur;
	}

	/**
	 * 
	 * @param currency
	 * @return
	 */
	public boolean isSupported(String currency) {
		return supCurrency.keySet().contains(currency);
	}

	/**
	 * 
	 * @param currency
	 * @return
	 * @throws CurrencyNotSupportedException
	 */
	public int getDecimalPlaceSupport(String currency) throws CurrencyNotSupportedException {

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

