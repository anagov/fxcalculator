package com.anz.securities.currency.main;

import com.anz.securities.conversionrate.dto.ConversionRates;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.currency.dto.SupportedCurrencies;

public class ApplicationData {
	
	private static SupportedCurrencies supportedCurrencies;
	private static ConversionRules conversionRules;
	private static ConversionRates conversionRates;
	
	public static ConversionRates getConversionRates() {
		return conversionRates;
	}


	public static void setConversionRates(ConversionRates conversionRates) {
		ApplicationData.conversionRates = conversionRates;
	}


	public static SupportedCurrencies getSupportedCurrencies() {
		return supportedCurrencies;
	}


	public static void setSupportedCurrencies( SupportedCurrencies supCurrencies ) {
		supportedCurrencies = supCurrencies;
	}


	public static ConversionRules getConversionRules() {
		return conversionRules;
	}
	
	public static void setConversionRules(ConversionRules conversionRules) {
		ApplicationData.conversionRules = conversionRules;
	}


}
