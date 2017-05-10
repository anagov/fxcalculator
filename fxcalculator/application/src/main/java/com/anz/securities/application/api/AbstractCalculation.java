package com.anz.securities.application.api;

import com.anz.securities.application.data.ApplicationCache;
import com.anz.securities.common.exception.ApplicationInitializationException;
import com.anz.securities.common.exception.CalculationException;
import com.anz.securities.common.exception.CurrencyNotSupportedException;
import com.anz.securities.common.exception.UndefinedConversionRate;
import com.anz.securities.common.exception.ValidationException;
import com.anz.securities.currency.dto.UserInputDto;

public abstract class AbstractCalculation implements Calculation {

	protected ApplicationCache cache;

	public void perform(UserInputDto userInput) throws CalculationException {
		try {
			initializaApplication();
			validateUserInput(userInput);
			determinePath(userInput);
			convertAmount(userInput);
		} catch (Exception ex) {
			throw new CalculationException(ex.getMessage());
		}
	}

	protected void initializaApplication() throws ApplicationInitializationException {
		cache = ApplicationCache.getInstance();
	}

	protected abstract void validateUserInput(UserInputDto userInput) throws ValidationException ;
	
	protected abstract void determinePath(UserInputDto userInput);

	protected abstract void convertAmount(UserInputDto userInput)
			throws UndefinedConversionRate, CurrencyNotSupportedException;

}
