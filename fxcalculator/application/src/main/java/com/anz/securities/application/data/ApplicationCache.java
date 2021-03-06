package com.anz.securities.application.data;

import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.conversionrate.api.ConversionRateLoader;
import com.anz.securities.conversionrate.dto.ConversionRates;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.conversionrule.factory.ConversionRuleLoaderFactory;
import com.anz.securities.currency.dto.SupportedCurrencies;
import com.anz.securities.currency.factory.CurrencyLoaderFactory;

import conm.anz.securities.conversionrate.factory.ConversionRateLoaderFactory;

/**
 * Stores application data
 * 
 * @author Anand Katti
 *
 */
public class ApplicationCache {

	private SupportedCurrencies supportedCurrencies;
	private ConversionRules conversionRules;
	private ConversionRates conversionRates;

	private static ApplicationCache cache = null;

	/**
	 * Prohibits external instantiation and loads the application data
	 * 
	 * @throws ApplicationInitializationException
	 */
	private ApplicationCache() throws ApplicationInitializationException {
		loadApplicationCache();
	}

	/**
	 * Returns the singleton instance of the class.
	 * 
	 * @return cache
	 * @throws ApplicationInitializationException
	 */
	public static ApplicationCache getInstance() throws ApplicationInitializationException {
		if (cache == null) {
			cache = new ApplicationCache();
		}
		return cache;
	}

	/**
	 * Utility method to provide conversion rates
	 * 
	 * @return conversionRates
	 */
	public ConversionRates getConversionRates() {
		return conversionRates;
	}

	/**
	 * Utility method to provide supported currencies
	 * 
	 * @return supportedCurrencies
	 */
	public SupportedCurrencies getSupportedCurrencies() {
		return supportedCurrencies;
	}

	/**
	 * Utility method to provide conversion rules.
	 * 
	 * @return conversionRules
	 */
	public ConversionRules getConversionRules() {
		return conversionRules;
	}

	/**
	 * Loads the application data
	 * 
	 * @throws ApplicationInitializationException
	 */
	private void loadApplicationCache() throws ApplicationInitializationException {
		supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader(Constants.XML_LOADER)
				.loadSupportedCurrencies();

		ConversionRateLoader rateLoader = ConversionRateLoaderFactory.getInstamce()
				.getRatesLoader(Constants.XML_LOADER);
		conversionRates = rateLoader.loadConversionRates();

		ConversionRuleLoader ruleLoader = ConversionRuleLoaderFactory.getInstamce()
				.getRulesLoader(Constants.XML_LOADER);
		conversionRules = ruleLoader.loadConversionRules();
	}
}
