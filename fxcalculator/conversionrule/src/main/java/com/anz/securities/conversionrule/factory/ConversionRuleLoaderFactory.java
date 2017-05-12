package com.anz.securities.conversionrule.factory;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.common.exception.LoaderNotSupportedException;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.loader.XMLConversionRuleLoader;

/**
 * 
 * @author xanakat
 *
 */
public class ConversionRuleLoaderFactory {
	
	private static final ConversionRuleLoaderFactory factory = new ConversionRuleLoaderFactory();
	 
	private ConversionRuleLoaderFactory() {
		
	}
	/**
	 * 
	 * @return
	 */
	public static ConversionRuleLoaderFactory getInstamce() {
		return factory;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 * @throws LoaderNotSupportedException
	 */
	public ConversionRuleLoader getRulesLoader( final LoaderType  source) throws LoaderNotSupportedException {
		
		if (source == LoaderType.XML ) {
			return new XMLConversionRuleLoader();
			
		} else {
			throw new LoaderNotSupportedException("Loader not supported");
		}
	}

}
