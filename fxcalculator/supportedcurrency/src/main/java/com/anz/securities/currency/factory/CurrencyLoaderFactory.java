package com.anz.securities.currency.factory;

import com.anz.securities.common.Constants;
import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.currency.api.CurrencyLoader;
import com.anz.securities.currency.loader.XMLCurrencyLoader;

/**
 * Factory to create supported currency loader instance
 * 
 * @author Anand Katti
 *
 */
public class CurrencyLoaderFactory {

	private static final CurrencyLoaderFactory factory = new CurrencyLoaderFactory();

	private CurrencyLoaderFactory() {
	}

	/**
	 * Returns the instance of the factory
	 * 
	 * @return factory
	 */
	public static synchronized CurrencyLoaderFactory getInstance() {
		return factory;
	}

	/**
	 * Returns the loader based on the loader type
	 * 
	 * @param source
	 * @return CurrencyLoader
	 * @throws LoaderNotSupportedException
	 */
	public CurrencyLoader getCurrencyLoader(final LoaderType source) throws LoaderNotSupportedException {

		if (source == Constants.XML_LOADER) {
			return new XMLCurrencyLoader();
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}
}
