package com.anz.securities.conversionrule.api;

import com.anz.securities.common.exception.DataLoaderException;
import com.anz.securities.conversionrule.dto.ConversionRules;

@FunctionalInterface
public interface ConversionRuleLoader {

	public ConversionRules loadConversionRules() throws DataLoaderException;
}