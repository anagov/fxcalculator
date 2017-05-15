package com.anz.securities.converter.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.Constants;
import com.anz.securities.common.exception.CurrencyConversionException;
import com.anz.securities.common.exception.RuleNotFoundException;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.converter.dto.TraversalPath;
import com.anz.securities.converter.dto.UserInputDto;
import com.anz.securities.entities.apii.ConversionRate;
import com.anz.securities.entities.apii.ConversionRule;
import com.anz.securities.entities.apii.Currency;
import com.anz.securities.entities.dto.CurrencyConverter;
import com.anz.securities.entities.impl.ConversionRuleImpl;

public class ConverterImpl extends AbstractConverter {
	private static Logger logger = LoggerFactory.getLogger(ConverterImpl.class);

	public ConverterImpl(CurrencyConverter converter) {
		super(converter);
	}

	@Override
	protected void validateUserInput(UserInputDto userInput) throws ValidationException {
		try {
			if (!converter.isCurrencySupported(userInput.getSourceCurrency())) {
				throw new ValidationException("Currency not supported - " + userInput.getSourceCurrency());
			}

			if (!converter.isCurrencySupported(userInput.getDestinationCurrency())) {
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

	@Override
	protected void determinePath(UserInputDto userInput) throws RuleNotFoundException {
		Map<String, Currency> currencyRuleMap = converter.getMapCurrecy();
		String sourceCurrency = userInput.getSourceCurrency();
		ConversionRule myrule;
		TraversalPath path;
		do {

			if ( userInput.getTraversedPath().contains(sourceCurrency)) {
				throw new RuleNotFoundException("Incorrect rule configuration");
			}
			List<ConversionRule> ruleList = currencyRuleMap.get(sourceCurrency).getRules();

			if (null == ruleList || ruleList.isEmpty()) {
				throw new RuleNotFoundException("Rule not found exception");
			}

			int index = Collections.binarySearch(ruleList, new ConversionRuleImpl(userInput.getDestinationCurrency(),""));

			if (index < 0) {
				throw new RuleNotFoundException("Rule not found exception");
			}

			myrule = ruleList.get(index);

			if (!myrule.getPointer().equals(Constants.END_RULE)) {
				path = new TraversalPath(sourceCurrency, myrule.getPointer());
				userInput.addTraversedPath(sourceCurrency);
				sourceCurrency = myrule.getPointer();
			} else {
				path = new TraversalPath(sourceCurrency, userInput.getDestinationCurrency());
			}

			userInput.addTraversal(path);
		} while (!myrule.getPointer().equals(Constants.END_RULE));
		
	}

	@Override
	protected void convertAmount(UserInputDto userInput) throws CurrencyConversionException {
		ConversionRates rates = cache.getConversionRates();
		ConversionRate rate;

		for (TraversalPath path : userInput.getTraversal()) {
			rate = rates.getConversionRate(path.getFromCurrency(), path.getToCurrency());
			applyCalculation(userInput, rate);
		}
	}

	private void applyCalculation(final UserInputDto userInput, final ConversionRate rate)
			throws CurrencyConversionException {

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
