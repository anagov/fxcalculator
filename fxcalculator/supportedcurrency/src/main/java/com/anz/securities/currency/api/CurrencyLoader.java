package com.anz.securities.currency.api;

import com.anz.securities.common.exception.CurrencyLoaderException;
import com.anz.securities.currency.dto.SupportedCurrencies;

@FunctionalInterface
public interface CurrencyLoader {
	public SupportedCurrencies loadSupportedCurrencies() throws CurrencyLoaderException;
}