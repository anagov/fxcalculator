package com.anz.securities.currency.factory;

import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.loader.XMLCurrencyLoader;

/**
 * 
 * @author xanakat
 *
 */
public class CurrencyLoaderFactory {
	
	private static final CurrencyLoaderFactory factory = new CurrencyLoaderFactory();

	private CurrencyLoaderFactory() {
		
	}
	/**
	 * 
	 * @return
	 */
	public static synchronized CurrencyLoaderFactory getInstance() {
		return factory;
	}
	
	/**
	 * 
	 * @return
	 */
	public CurrencyLoader getCurrencyLoader() {
		return new XMLCurrencyLoader();
	}
}
