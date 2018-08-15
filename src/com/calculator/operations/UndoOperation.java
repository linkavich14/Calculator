package com.calculator.operations;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.CalculatorException;
import com.calculator.model.CalculatorBean;

/**
 * <p>This class undoes the previous operation only, it does not undo the previous number entered.
 * 	  It is highly coupled with the CalculatorRPN class
 * </p>
 * @author Juan Hernandez
 *
 */
@Component("Undo")
@Scope("prototype")
public class UndoOperation implements Operation{
	@Autowired
	private CalculatorBean calc;
	
	/**
	 * The execute method does not require any of the values to undo an operation. 
	 * This method handles the operation stack and makes changes to it.
	 * @return returns null if the undo operation was successful 
	 */
	@Override
	public String execute(String firstValue, String secondValue) throws Exception {
		Stack <String> numbersStack = calc.getNumbersStack();
		Stack <Operation> operationsStack = calc.getOperationStack();		
		if (operationsStack.isEmpty() && numbersStack.isEmpty()) {
			throw new CalculatorException("no operations or values to remove");
		}		
		
		Operation operation = null;
		
		if (!operationsStack.isEmpty()) {
			operation = operationsStack.pop();
		}
		
		if (operation != null) {
			if (operation.getSecondValue() != null) {
				numbersStack.push(operation.getSecondValue());
			}	
			numbersStack.push(operation.getFirstValue());
		}		
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
