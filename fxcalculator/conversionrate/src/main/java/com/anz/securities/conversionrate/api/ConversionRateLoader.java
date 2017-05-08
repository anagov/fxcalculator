package com.anz.securities.conversionrate.api;

import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrate.dto.ConversionRates;

@FunctionalInterface
public interface ConversionRateLoader {
	
	public ConversionRates loadConversionRates() throws DataLoaderException;
}


