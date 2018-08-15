package com.calculator.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.model.CalculatorBean;

/**
 * <p>This operation takes care of clearing the stacks of 
 * 	  numbers and operations for the calculator. </p>
 * @author Juan Hernandez
 */
@Component("Clear")
@Scope("prototype")
public class ClearOperation implements Operation{
	
	@Autowired
	private CalculatorBean calc;
	/**
	 * Clears the values of the numbers and operations stacks, it does not make use of any of the values
	 */
	@Override
	public String execute(String firstValue, String secondValue) throws Exception {
		calc.getNumbersStack().clear();
		calc.getOperationStack().clear();
		return null;		
	}

	@Override
	public String getFirstValue() {		
		return null;
	}

	@Override
	public String getSecondValue() {	
		return null;
	}

}
