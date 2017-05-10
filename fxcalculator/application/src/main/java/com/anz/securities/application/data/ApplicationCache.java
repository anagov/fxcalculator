package com.anz.securities.application.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class ApplicationCache {
	private static Logger logger = LoggerFactory.getLogger(ApplicationCache.class);

	private SupportedCurrencies supportedCurrencies;
	private ConversionRules conversionRules;
	private ConversionRates conversionRates;

	private static ApplicationCache cache = null;

	private ApplicationCache() throws ApplicationInitializationException {
		loadApplicationCache();
	}

	public static ApplicationCache getInstance() throws ApplicationInitializationException {
		if (cache == null) {
			cache = new ApplicationCache();
		}
		return cache;
	}

	public ConversionRates getConversionRates() {
		return conversionRates;
	}

	public SupportedCurrencies getSupportedCurrencies() {
		return supportedCurrencies;
	}

	public ConversionRules getConversionRules() {
		return conversionRules;
	}

	public void loadApplicationCache() throws ApplicationInitializationException {
		try {
			supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader().loadSupportedCurrencies();

			LoaderType source = LoaderType.XML;
			ConversionRateLoader rateLoader = ConversionRateLoaderFactory.getInstamce().getRatesLoader(source);
			conversionRates = rateLoader.loadConversionRates();

			ConversionRuleLoader ruleLoader = ConversionRuleLoaderFactory.getInstamce().getRulesLoader(source);
			conversionRules = ruleLoader.loadConversionRules();

		} catch (Exception ex) {
			logger.error("Application initialization failed" + ex);
			throw new ApplicationInitializationException(ex.getMessage());
		}
	}
}
