package com.anz.securities.application.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.Constants;
import com.anz.securities.common.dto.LoaderType;
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
 * 
 * @author xanakat
 *
 */
public class ApplicationCache {
	private static Logger logger = LoggerFactory.getLogger(ApplicationCache.class);

	private SupportedCurrencies supportedCurrencies;
	private ConversionRules conversionRules;
	private ConversionRates conversionRates;

	private static ApplicationCache cache = null;

	/**
	 * 
	 * @throws ApplicationInitializationException
	 */
	private ApplicationCache() throws ApplicationInitializationException {
		loadApplicationCache();
	}

	/**
	 * 
	 * @return
	 * @throws ApplicationInitializationException
	 */
	public static ApplicationCache getInstance() throws ApplicationInitializationException {
		if (cache == null) {
			cache = new ApplicationCache();
		}
		return cache;
	}

	/**
	 * 
	 * @return
	 */
	public ConversionRates getConversionRates() {
		return conversionRates;
	}

	/**
	 * 
	 * @return
	 */
	public SupportedCurrencies getSupportedCurrencies() {
		return supportedCurrencies;
	}

	/**
	 * 
	 * @return
	 */
	public ConversionRules getConversionRules() {
		return conversionRules;
	}

	/**
	 * 
	 * @throws ApplicationInitializationException
	 */
	private void loadApplicationCache() throws ApplicationInitializationException {
		try {
			supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader(Constants.XML_LOADER)
					.loadSupportedCurrencies();

			ConversionRateLoader rateLoader = ConversionRateLoaderFactory.getInstamce()
					.getRatesLoader(Constants.XML_LOADER);
			conversionRates = rateLoader.loadConversionRates();

			ConversionRuleLoader ruleLoader = ConversionRuleLoaderFactory.getInstamce()
					.getRulesLoader(Constants.XML_LOADER);
			conversionRules = ruleLoader.loadConversionRules();

		} catch (Exception ex) {
			logger.error("Application initialization failed" + ex);
			throw new ApplicationInitializationException(ex.getMessage());
		}
	}
}
