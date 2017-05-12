package com.anz.securities.application.api;

import com.anz.securities.application.data.ApplicationCache;
import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.CalculationException;
import com.anz.securities.common.exception.CurrencyNotSupportedException;
import com.anz.securities.common.exception.UndefinedConversionRate;
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
	public void perform(final UserInputDto userInput) throws CalculationException {
		try {
			initializaApplication();
			validateUserInput(userInput);
			determinePath(userInput);
			convertAmount(userInput);
		} catch (Exception ex) {
			throw new CalculationException(ex.getMessage());
		}
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
	protected abstract void validateUserInput(final UserInputDto userInput) throws ValidationException ;
	
	/**
	 * 
	 * @param userInput
	 */
	protected abstract void determinePath(final UserInputDto userInput);

	/**
	 * 
	 * @param userInput
	 * @throws UndefinedConversionRate
	 * @throws CurrencyNotSupportedException
	 */
	protected abstract void convertAmount(final UserInputDto userInput)
			throws UndefinedConversionRate, CurrencyNotSupportedException;

}
