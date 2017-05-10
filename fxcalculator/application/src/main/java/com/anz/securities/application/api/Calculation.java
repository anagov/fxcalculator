package com.anz.securities.application.api;

import com.anz.securities.common.exception.CalculationException;
import com.anz.securities.currency.dto.UserInputDto;

public interface Calculation {

	public void perform(UserInputDto userInput) throws CalculationException;
}
