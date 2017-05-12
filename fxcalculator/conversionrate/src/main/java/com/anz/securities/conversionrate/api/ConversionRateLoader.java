package com.anz.securities.conversionrate.api;

import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrate.dto.ConversionRates;
/**
 * 
 * @author xanakat
 *
 */
@FunctionalInterface
public interface ConversionRateLoader {
	/**
	 * 
	 * @return
	 * @throws DataLoaderException
	 */
	public ConversionRates loadConversionRates() throws DataLoaderException;
}


