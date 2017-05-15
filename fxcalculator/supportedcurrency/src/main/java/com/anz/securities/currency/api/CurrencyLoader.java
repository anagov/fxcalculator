package com.anz.securities.currency.api;

import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.currency.dto.SupportedCurrencies;

/**
 * Type definition for loading supported currencies
 * 
 * @author Anand Katti
 *
 */
@FunctionalInterface
public interface CurrencyLoader {
	/**
	 * Loads supported currencies
	 * 
	 * @return SupportedCurrencies
	 * @throws CurrencyLoaderException
	 */
	public SupportedCurrencies loadSupportedCurrencies() throws DataLoaderException;
}