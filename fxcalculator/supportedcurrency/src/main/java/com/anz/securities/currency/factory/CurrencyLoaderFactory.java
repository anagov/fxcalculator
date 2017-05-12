package com.anz.securities.currency.factory;

import com.anz.securities.common.Constants;
import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
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
	public CurrencyLoader getCurrencyLoader(final LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == Constants.XML_LOADER ) {
			return new XMLCurrencyLoader();
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}
}
