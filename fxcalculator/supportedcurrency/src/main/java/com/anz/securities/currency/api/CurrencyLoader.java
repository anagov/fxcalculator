package com.anz.securities.currency.api;

import com.anz.securities.common.exception.CurrencyLoaderException;
import com.anz.securities.currency.dto.SupportedCurrencies;

/**
 * 
 * @author xanakat
 *
 */
@FunctionalInterface
public interface CurrencyLoader {
	/**
	 * 
	 * @return
	 * @throws CurrencyLoaderException
	 */
	public SupportedCurrencies loadSupportedCurrencies() throws CurrencyLoaderException;
}