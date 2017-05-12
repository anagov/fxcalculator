package com.anz.securities.currency.main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anz.securities.application.api.Calculation;
import com.anz.securities.application.impl.CalculationImpl;
import com.anz.securities.currency.dto.UserInputDto;

public class FXCalculator {
	private static Logger logger = LoggerFactory.getLogger(FXCalculator.class);

	public static void main(String[] args) {
		try {
			String sourceCurrency = "AUD";
			String destCurrency = "USD";
			String amount = "120";
			
			Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter Source Currency:" );  
	        sourceCurrency = scanner.next();
	        
	        System.out.println("Enter Destination Currency:" );  
	        destCurrency = scanner.next();

	        System.out.println("Enter Amount to be converted:" );  
	        amount = scanner.next();

	        
			UserInputDto userDto = new UserInputDto();
			
			userDto.setConversionAmount(amount); 
			userDto.setSourceCurrency(sourceCurrency);
			userDto.setDestinationCurrency(destCurrency);
			
			Calculation calculation = new CalculationImpl();
			calculation.perform(userDto);
			scanner.close();
			logger.info("Converted Amount is =======" + userDto.getConvertedAmount());
			
		} catch (Exception ex) {
			logger.error("Error running main application" + ex);
		}
	}

}
