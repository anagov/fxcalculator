package com.anz.securities.conversionrule.api;

import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrule.dto.ConversionRules;

/**
 * 
 * @author xanakat
 *
 */
@FunctionalInterface
public interface ConversionRuleLoader {

	/**
	 * 
	 * @return
	 * @throws DataLoaderException
	 */
	public ConversionRules loadConversionRules() throws DataLoaderException;
}