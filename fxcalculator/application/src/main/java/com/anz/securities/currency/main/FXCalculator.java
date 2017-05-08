package com.anz.securities.currency.main;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.conversionrule.dto.ConversionRule;
import com.anz.securities.conversionrule.dto.ConversionRules;
import com.anz.securities.currency.dto.UserInputDto;

public class FXCalculator {
	private static Logger logger = LoggerFactory.getLogger(FXCalculator.class);

	public FXCalculator() throws ApplicationInitializationException {
		ApplicationInitializer.init();
	}

	public static void main(String[] args) {
		try {

			String sourceCurrency = "CNY";
			String destCurrency = "DKK";
			String amount = "120";
			
			FXCalculator fxCalculator = new FXCalculator();
			UserInputDto userDto = fxCalculator.validateUserInput(sourceCurrency, destCurrency, amount);
			fxCalculator.performConversion(userDto);
			
		} catch (Exception ex) {
			logger.error("Error running main application" + ex);
		}
	}

	private UserInputDto validateUserInput(String srcCurrency, String destCurrency, String amt)
			throws ValidationException {
		UserInputDto userDto = new UserInputDto();
		try {
			if ( ! ApplicationData.getSupportedCurrencies().isSupported(srcCurrency) ) {
				throw new ValidationException("Currency not supported - " + srcCurrency);
			}
			
			if ( ! ApplicationData.getSupportedCurrencies().isSupported(destCurrency) ) {
				throw new ValidationException("Currency not supported - " + destCurrency);				
			}
			
			double amount = Double.parseDouble(amt);
			
			userDto.setConversionAmount(amount);
			userDto.setSourceCurrency(srcCurrency);
			userDto.setDestinationCurrency(destCurrency);
			
		} catch (NumberFormatException exNumberFormat) {
			logger.error("Incorrect amount format" + exNumberFormat);
			throw new ValidationException("Incorrect amount format" + exNumberFormat.getMessage());
		} catch ( Exception ex ) {
			logger.error("Generic error" + ex);
			throw new ValidationException("Generic Error" + ex.getMessage());			
		}
		return userDto;
	}

	private void performConversion(UserInputDto userDto) {
		
	}
}
