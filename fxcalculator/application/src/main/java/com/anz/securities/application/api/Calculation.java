package com.anz.securities.application.api;

import com.anz.securities.common.exception.FxCalculatorException;
import com.anz.securities.currency.dto.UserInputDto;

/**
 * Type to provide conversion from source currency to destination currency
 * @author xanakat
 *
 */
@FunctionalInterface
public interface Calculation {

	/**
	 * Performs the conversion from source currency to destination currency
	 * @param userInput
	 * @throws CalculationException
	 */
	public void perform( final UserInputDto userInput) throws FxCalculatorException;
}
