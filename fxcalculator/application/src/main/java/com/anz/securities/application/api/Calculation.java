package com.anz.securities.application.api;

import com.anz.securities.common.exception.CalculationException;
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
	public void perform(UserInputDto userInput) throws CalculationException;
}
