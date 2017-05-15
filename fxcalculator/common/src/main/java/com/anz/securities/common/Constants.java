package com.anz.securities.common;

import com.anz.securities.common.dto.LoaderType;

/**
 * Constants Declarations.
 * 
 * @author Anand Katti
 *
 */
public class Constants {

	public static final String SUPPORTED_CURRENCIES_RESOURCE_FILE = "SupportedCurrencies.xml";
	public static final String CONVERSION_RULE_RESOURCE_FILE = "ConversionRules.xml";
	public static final String CONVERSION_RATE_RESOURCE_FILE = "ConversionRate.xml";
	public static final String SOURCE_CURRENCY = "sourcecurrency";
	public static final String DESTINATION_CURRENCY = "destinationcurrency";
	public static final String ID = "id";
	public static final String CONVERTION_RATE = "conversionrate";
	public static final String RATE = "rate";
	public static final String LINKED_TO = "linkedto";
	public static final String SUPPORTED_DECIMAL = "supportedDecimal";
	public static final String CURRENCY = "currency";
	public static final LoaderType XML_LOADER = LoaderType.XML;
	public static final String CONV_DIRECT = "D";
	public static final String CONV_INVERT = "INV";
	public static final String END_RULE = "NA";

}
