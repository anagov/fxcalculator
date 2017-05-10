package com.anz.securities.currency.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.CurrencyNotSupportedException;
import com.anz.securities.common.exception.UndefinedConversionRate;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.conversionrate.dto.ConversionRate;
import com.anz.securities.conversionrate.dto.ConversionRates;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.currency.dto.TraversalPath;
import com.anz.securities.currency.dto.UserInputDto;

public class FXCalculator {
	private static Logger logger = LoggerFactory.getLogger(FXCalculator.class);

	public FXCalculator() throws ApplicationInitializationException {
		ApplicationInitializer.init();
	}

	public static void main(String[] args) {
		try {

			String sourceCurrency = "CNY";
			String destCurrency = "JPY";
			String amount = "120";

			FXCalculator fxCalculator = new FXCalculator();
			UserInputDto userDto = fxCalculator.validateUserInput(sourceCurrency, destCurrency, amount);
			fxCalculator.performConversion(userDto);
			logger.info("Converted Amount is =======" + userDto.getConvertedAmount());
			
		} catch (Exception ex) {
			logger.error("Error running main application" + ex);
		}
	}

	private UserInputDto validateUserInput(String srcCurrency, String destCurrency, String amt)
			throws ValidationException {
		UserInputDto userDto = new UserInputDto();
		try {
			if (!ApplicationData.getSupportedCurrencies().isSupported(srcCurrency)) {
				throw new ValidationException("Currency not supported - " + srcCurrency);
			}

			if (!ApplicationData.getSupportedCurrencies().isSupported(destCurrency)) {
				throw new ValidationException("Currency not supported - " + destCurrency);
			}

			double amount = Double.parseDouble(amt);
			userDto.setConversionAmount(amount);
			userDto.setConvertedAmount(amount);
			userDto.setSourceCurrency(srcCurrency);
			userDto.setDestinationCurrency(destCurrency);

		} catch (NumberFormatException exNumberFormat) {
			logger.error("Incorrect amount format" + exNumberFormat);
			throw new ValidationException("Incorrect amount format" + exNumberFormat.getMessage());
		} catch (Exception ex) {
			logger.error("Generic error" + ex);
			throw new ValidationException("Generic Error" + ex.getMessage());
		}
		return userDto;
	}

	private void performConversion(UserInputDto inputDto) throws UndefinedConversionRate, CurrencyNotSupportedException {

		findPath(inputDto);
		convertAmount(inputDto);
	}
	

	private void findPath(UserInputDto inputDto) {
		ConversionRules conversionRules = ApplicationData.getConversionRules();
		String sourceCurrency = inputDto.getSourceCurrency();
		ConversionRule myrule;
		TraversalPath path;
		do {

			List<ConversionRule> ruleList = conversionRules.getRule(sourceCurrency);
			int index = Collections.binarySearch(ruleList, new ConversionRule(inputDto.getDestinationCurrency()));
			myrule = ruleList.get(index);
			//logger.info("Rule details ---- " + myrule.toString());

			if (!myrule.getLinkedTo().equals("NA")) {
				path = new TraversalPath(sourceCurrency, myrule.getLinkedTo(), myrule.getConversionType());
				sourceCurrency = myrule.getLinkedTo();
			} else {
				path = new TraversalPath(sourceCurrency, inputDto.getDestinationCurrency(), myrule.getConversionType());
			}
/*			logger.info("FROM - " + path.getFromCurrency());
			logger.info("TO -" + path.getToCurrency());
			logger.info("Convertion Type" + path.getConversionType());*/
			inputDto.addTraversal(path);
		} while (!myrule.getLinkedTo().equals("NA"));
	}

	private void convertAmount(UserInputDto inputDto) throws UndefinedConversionRate,CurrencyNotSupportedException {

		ConversionRates rates = ApplicationData.getConversionRates();
		ConversionRate rate;

		for (TraversalPath path : inputDto.getTraversal()) {
/*			logger.info("FROM - " + path.getFromCurrency());
			logger.info("TO -" + path.getToCurrency());
			logger.info("Convertion Type" + path.getConversionType());*/


			rate = rates.getConversionRate(path.getFromCurrency(), path.getToCurrency());
			applyCalculation(inputDto, rate);
			//logger.info("Rate obtained is " + rate.toString());

		}
	}
	
	private void applyCalculation(UserInputDto userInput, ConversionRate rate)  throws CurrencyNotSupportedException {

		logger.info("Conversion amount " + userInput.getConvertedAmount());
		logger.info("Conversion Rate " + rate.getConversionRate() );
		logger.info("Conversion Type " + rate.getConversionType() );

		double convertedAmt=0;
		int expectedDecimal = ApplicationData.getSupportedCurrencies().getDecimalPlaceSupport(rate.getDestinationCurrency());
		
		if ( rate.getConversionType().equalsIgnoreCase("D")) {
			
			convertedAmt = userInput.getConvertedAmount() * Double.valueOf(rate.getConversionRate());
			
		} else if ( rate.getConversionType().equalsIgnoreCase("INV")) {
			convertedAmt = userInput.getConvertedAmount() * (1 / Double.valueOf(rate.getConversionRate()));
		}
		
		userInput.setConvertedAmount(new BigDecimal(convertedAmt).setScale(expectedDecimal, RoundingMode.HALF_UP).doubleValue());
		
		logger.info("Converted amount " + convertedAmt);
	}
}
