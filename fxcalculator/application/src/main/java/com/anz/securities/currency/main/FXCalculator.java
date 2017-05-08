package com.anz.securities.currency.main;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.dto.LoaderType;
import com.anz.securities.conversionrule.api.ConversionRuleLoader;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.conversionrule.factory.ConversionRuleLoaderFactory;
import com.anz.securities.currency.dto.SupportedCurrencies;
import com.anz.securities.currency.factory.CurrencyLoaderFactory;

public class FXCalculator {
	private static Logger logger = LoggerFactory.getLogger(FXCalculator.class);
	
	public static void main(String[] args) {
		try {
			
			String sourceCurrency = "CNY";
			String destCurrency = "DKK";
			String amount = "120";
			
			SupportedCurrencies supportedCurrencies = CurrencyLoaderFactory.getInstance().getCurrencyLoader()
					.loadSupportedCurrencies();
			logger.info("Currencies loaded are" + supportedCurrencies.toString());
			
			LoaderType source = LoaderType.XML;
			ConversionRuleLoader loader = ConversionRuleLoaderFactory.getInstamce().getRulesLoader(source);
			ConversionRules rules = loader.loadConversionRules();
			
			findPath(rules, sourceCurrency, destCurrency);
		
		} catch ( Exception ex) {
			
		}
	}
	
	private static void findPath(ConversionRules rules, String src, String dest) {
		ConversionRule myrule;
		String temp = src;
		do {
			//logger.info("temp --" + temp);
			List<ConversionRule> ruleList = rules.getRule(temp);
			//logger.info("----" + ruleList.toString() + "-----");
			//Collections.sort(ruleList);
			int index = Collections.binarySearch(ruleList, new ConversionRule(dest));
			//logger.info("----" + index + "-----");
			myrule = ruleList.get(index);
		//	logger.info("----" + myrule.toString() + "-----");
			logger.info("----" + temp + "-----");;
			if (! myrule.getLinkedTo().equals("NA") ) {
				logger.info("----" + myrule.getLinkedTo() + "-----");
				
			} else {
				logger.info("----" + dest + "-----");;
			}
			temp = myrule.getLinkedTo();
			//logger.info("----" + myrule.getLinkedTo() + "-----");
			
		} while(! myrule.getLinkedTo().equals("NA") );

	}

}
