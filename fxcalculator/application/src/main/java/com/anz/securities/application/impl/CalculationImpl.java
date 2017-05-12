package com.anz.securities.application.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.application.api.AbstractCalculation;
import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.CurrencyConversionException;
import com.anz.securities.common.exception.RuleNotFoundException;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.conversionrate.dto.ConversionRate;
import com.anz.securities.conversionrate.dto.ConversionRates;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.currency.dto.TraversalPath;
import com.anz.securities.currency.dto.UserInputDto;

/**
 * 
 * @author xanakat
 *
 */
public class CalculationImpl extends AbstractCalculation {
	private static Logger logger = LoggerFactory.getLogger(CalculationImpl.class);

	/**
	 * 
	 */
	protected void validateUserInput( final UserInputDto userInput) throws ValidationException {
		try {
			if (!cache.getSupportedCurrencies().isSupported(userInput.getSourceCurrency())) {
				throw new ValidationException("Currency not supported - " + userInput.getSourceCurrency());
			}

			if (!cache.getSupportedCurrencies().isSupported(userInput.getDestinationCurrency())) {
				throw new ValidationException("Currency not supported - " + userInput.getDestinationCurrency());
			}

			double amount = Double.parseDouble(userInput.getConversionAmount());
			logger.info("ConversionA mount is " + amount);
			userInput.setConvertedAmount(amount);
		} catch (NumberFormatException exNumberFormat) {
			logger.error("Incorrect amount format" + exNumberFormat);
			throw new ValidationException("Incorrect amount format" + exNumberFormat.getMessage());
		} catch (Exception ex) {
			logger.error("Generic error" + ex);
			throw new ValidationException("Generic Error" + ex.getMessage());
		}
	}

	/**
	 * 
	 */
	protected void determinePath(final UserInputDto userInput) throws RuleNotFoundException {
		ConversionRules conversionRules = cache.getConversionRules();
		String sourceCurrency = userInput.getSourceCurrency();
		ConversionRule myrule;
		TraversalPath path;
		do {

			List<ConversionRule> ruleList = conversionRules.getRule(sourceCurrency);
		
			if ( null == ruleList || ruleList.isEmpty()) {
				throw new RuleNotFoundException("Rule not found exception");
			}
			
			int index = Collections.binarySearch(ruleList, new ConversionRule(userInput.getDestinationCurrency()));
			
			if ( index <= 0) {
				throw new RuleNotFoundException("Rule not found exception");
			}
			
			myrule = ruleList.get(index);

			if (!myrule.getLinkedTo().equals(Constants.END_RULE)) {
				path = new TraversalPath(sourceCurrency, myrule.getLinkedTo());
				sourceCurrency = myrule.getLinkedTo();
			} else {
				path = new TraversalPath(sourceCurrency, userInput.getDestinationCurrency());
			}

			userInput.addTraversal(path);
		} while (!myrule.getLinkedTo().equals(Constants.END_RULE));
	}

	/**
	 * 
	 */
	@Override
	protected void convertAmount(final UserInputDto userInput) throws CurrencyConversionException {
		ConversionRates rates = cache.getConversionRates();
		ConversionRate rate;

		for (TraversalPath path : userInput.getTraversal()) {
			rate = rates.getConversionRate(path.getFromCurrency(), path.getToCurrency());
			applyCalculation(userInput, rate);
		}
	}

	private void applyCalculation(final UserInputDto userInput, final ConversionRate rate) throws CurrencyConversionException {

		logger.info("Conversion amount " + userInput.getConvertedAmount());
		logger.info("Conversion Rate " + rate.getConversionRate());
		logger.info("Conversion Type " + rate.getConversionType());

		double convertedAmt = 0;
		int expectedDecimal = cache.getSupportedCurrencies().getDecimalPlaceSupport(rate.getDestinationCurrency());

		if (rate.getConversionType().equalsIgnoreCase(Constants.CONV_DIRECT)) {

			convertedAmt = userInput.getConvertedAmount() * Double.valueOf(rate.getConversionRate());

		} else if (rate.getConversionType().equalsIgnoreCase(Constants.CONV_INVERT)) {
			convertedAmt = userInput.getConvertedAmount() * (1 / Double.valueOf(rate.getConversionRate()));
		}

		userInput.setConvertedAmount(
				new BigDecimal(convertedAmt).setScale(expectedDecimal, RoundingMode.HALF_UP).doubleValue());

		logger.info("Converted amount " + convertedAmt);
	}

}
