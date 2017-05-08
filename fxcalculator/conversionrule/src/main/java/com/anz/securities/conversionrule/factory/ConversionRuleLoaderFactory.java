package com.anz.securities.conversionrule.factory;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.loader.XMLConversionRuleLoader;

public class ConversionRuleLoaderFactory {
	
	private static ConversionRuleLoaderFactory factory;
	 
	public static ConversionRuleLoaderFactory getInstamce() {
		if ( factory == null ) {
			factory = new ConversionRuleLoaderFactory();
		}
		return factory;
	}
	
	public ConversionRuleLoader getRulesLoader( LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == LoaderType.XML ) {
			return new XMLConversionRuleLoader();
			
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}

}
