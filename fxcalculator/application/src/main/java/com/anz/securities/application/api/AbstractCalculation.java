package com.anz.securities.application.api;

import com.anz.securities.application.data.ApplicationCache;
import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.CurrencyConversionException;
import com.anz.securities.common.exception.FxCalculatorException;
import com.anz.securities.common.exception.RuleNotFoundException;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.currency.dto.UserInputDto;

/**
 * Provides a template for performing the calculation
 * 
 * @author Anand Katti
 *
 */
public abstract class AbstractCalculation implements Calculation {

	protected ApplicationCache cache;

	/**
	 * {@see Calculation.perform}
	 */
	public void perform(final UserInputDto userInput) throws FxCalculatorException {
		initializaApplication();
		validateUserInput(userInput);
		determinePath(userInput);
		convertAmount(userInput);
	}

	/**
	 * Initializes the application with data
	 * 
	 * @throws ApplicationInitializationException
	 */
	private void initializaApplication() throws ApplicationInitializationException {
		cache = ApplicationCache.getInstance();
	}

	/**
	 * template method to validate user input
	 * 
	 * @param userInput
	 * @throws ValidationException
	 */
	protected abstract void validateUserInput(final UserInputDto userInput) throws ValidationException;

	/**
	 * template method to determine path from source currency to destination
	 * currency
	 * 
	 * @param userInput
	 */
	protected abstract void determinePath(final UserInputDto userInput) throws RuleNotFoundException;

	/**
	 * Template method to convert the amount from source currency to destination
	 * currency
	 * 
	 * @param userInput
	 * @throws CurrencyConversionException
	 */
	protected abstract void convertAmount(final UserInputDto userInput) throws CurrencyConversionException;

}
