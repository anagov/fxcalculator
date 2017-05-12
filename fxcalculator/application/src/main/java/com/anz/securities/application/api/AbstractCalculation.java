package com.anz.securities.application.api;

import com.anz.securities.application.data.ApplicationCache;
import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.CurrencyConversionException;
import com.anz.securities.common.exception.FxCalculatorException;
import com.anz.securities.common.exception.RuleNotFoundException;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.currency.dto.UserInputDto;

/**
 * 
 * @author xanakat
 *
 */
public abstract class AbstractCalculation implements Calculation {

	protected ApplicationCache cache;

	/**
	 * 
	 */
	public void perform(final UserInputDto userInput) throws FxCalculatorException {
		initializaApplication();
		validateUserInput(userInput);
		determinePath(userInput);
		convertAmount(userInput);
	}

	/**
	 * 
	 * @throws ApplicationInitializationException
	 */
	protected void initializaApplication() throws ApplicationInitializationException {
		cache = ApplicationCache.getInstance();
	}

	/**
	 * 
	 * @param userInput
	 * @throws ValidationException
	 */
	protected abstract void validateUserInput(final UserInputDto userInput) throws ValidationException;

	/**
	 * 
	 * @param userInput
	 */
	protected abstract void determinePath(final UserInputDto userInput) throws RuleNotFoundException;

	/**
	 * 
	 * @param userInput
	 * @throws CurrencyConversionException
	 */
	protected abstract void convertAmount(final UserInputDto userInput) throws CurrencyConversionException;

}
