package com.anz.securities.application.api;

import com.anz.securities.common.exception.FxCalculatorException;
import com.anz.securities.currency.dto.UserInputDto;

/**
 * 
 * @author xanakat
 *
 */
@FunctionalInterface
public interface Calculation {

	/**
	 * 
	 * @param userInput
	 * @throws CalculationException
	 */
	public void perform( final UserInputDto userInput) throws FxCalculatorException;
}
