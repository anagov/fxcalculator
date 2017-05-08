package com.anz.securities.currency.main;

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

public class ApplicationInitializer {
	private static Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	public static void init() throws ApplicationInitializationException {
		try {
			SupportedCurrencies supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader()
					.loadSupportedCurrencies();
			ApplicationData.setSupportedCurrencies(supportedCurrencies);

			LoaderType source = LoaderType.XML;
			ConversionRateLoader rateLoader = ConversionRateLoaderFactory.getInstamce().getRatesLoader(source);
			ConversionRates convRates = rateLoader.loadConversionRates();
			ApplicationData.setConversionRates(convRates);

			ConversionRuleLoader ruleLoader = ConversionRuleLoaderFactory.getInstamce().getRulesLoader(source);
			ConversionRules rules = ruleLoader.loadConversionRules();
			ApplicationData.setConversionRules(rules);

		} catch (Exception ex) {
			logger.error("Application initialization failed" + ex);
			throw new ApplicationInitializationException(ex.getMessage());
		}
	}
}
