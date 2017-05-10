package com.anz.securities.currency.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.application.api.Calculation;
import com.anz.securities.application.impl.CalculationImpl;
import com.anz.securities.currency.dto.UserInputDto;

public class FXCalculator {
	private static Logger logger = LoggerFactory.getLogger(FXCalculator.class);

	public static void main(String[] args) {
		try {

			String sourceCurrency = "CNY";
			String destCurrency = "JPY";
			String amount = "120";
			
			UserInputDto userDto = new UserInputDto();
			
			userDto.setConversionAmount(amount); 
			userDto.setSourceCurrency(sourceCurrency);
			userDto.setDestinationCurrency(destCurrency);
			
			Calculation calculation = new CalculationImpl();
			calculation.perform(userDto);
			
			logger.info("Converted Amount is =======" + userDto.getConvertedAmount());
			
		} catch (Exception ex) {
			logger.error("Error running main application" + ex);
		}
	}

}
