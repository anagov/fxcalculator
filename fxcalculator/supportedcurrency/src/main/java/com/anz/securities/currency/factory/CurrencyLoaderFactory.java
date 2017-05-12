package com.anz.securities.currency.factory;

import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.loader.XMLCurrencyLoader;

/**
 * 
 * @author xanakat
 *
 */
public class CurrencyLoaderFactory {
	
	private volatile static CurrencyLoaderFactory factory = null;

	/**
	 * 
	 * @return
	 */
	public static synchronized CurrencyLoaderFactory getInstance() {
		if ( factory == null ) {
			factory = new CurrencyLoaderFactory();
		}
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
