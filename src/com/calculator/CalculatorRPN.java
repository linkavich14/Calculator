package com.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.CalculatorException;
import com.calculator.exceptions.InsufficientParametersException;
import com.calculator.exceptions.InvalidOperationException;
import com.calculator.model.CalculatorBean;
import com.calculator.operations.Operation;
import com.calculator.readers.CommandReader;
import com.calculator.utils.CommonLiterals;
import com.calculator.utils.Utils;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author Juan Hernandez
 */
@Component
public class CalculatorRPN {
		
	@Autowired
	private CommonLiterals literals;	
	@Autowired
	private ApplicationContext context;
	@Autowired
	private CommandReader commandReader;
	
	@Autowired
	@JsonBackReference
	private CalculatorBean calc;

	/**
	 * Gets the command from the command line and send them to evaluate them
	 */
	public void initCommandReader() throws Exception {
		try {
            String command = null;
            while ((command = commandReader.getFollowingCommand()) != null) {
                if (checkCommand(command)) {
                    evaluateString(command);
                }else {
                	System.out.println("Invalid Command");
                }
            }
        } finally {
            commandReader.finishReader();
        }		
	}
	
	/**
	 * Parses the string to get the numbers and operations from it so later can be executed.
	 * Also inserts numeric values into the numbers stack so they can be used later for the operations
	 * @param input String from the command line
	 * @return the insufficient exception text, only used to test operator errors of the operations 
	 */
	public String evaluateString(String input)  {		
		String[] results = input.split("\\s");
		int index = 0;
			
		try {
			for (String result : results) {
				index++;
				if (Utils.isNumber(result)) {					
					calc.getNumbersStack().push(result);
					calc.getOperationStack().push(null);
				}else {
					executeOperation(result);
				}
			}
		} catch (InsufficientParametersException ipe) {
			StringBuilder exceptionMessage = new StringBuilder();
			exceptionMessage.append("operator ").append(ipe.getMessage()).append(" (position: ").append(index).append("): insuficient parameters");
			System.out.println(exceptionMessage.toString());
			calc.setExceptionMessage(exceptionMessage.toString());
			return exceptionMessage.toString();
		} catch (Exception e) {
			calc.setExceptionMessage(e.getMessage());
			System.out.println(e.getMessage());
		}		
		finally {
			printStack();
		}
		calc.setExceptionMessage("");
		return "";
	}
	
	/**
	 * Executes an operation and inserts it in a stack for later use if the necessary 
	 * @param operator one of the operation symbols + - * / sqrt clear 
	 */
	public void executeOperation(String operator) throws Exception {
		if (calc.getNumbersStack().isEmpty()) {
			throw new CalculatorException("Cannot process an operation with an empty stack");
		}
		
		if (literals.getOperationsMap().get(operator) == null) {
			throw new InvalidOperationException(operator + " is not a valid operator");
		}
		
		if (operator == null) {
			throw new CalculatorException("Null parameter");
		}
				
		Operation operation = context.getBean(literals.getOperationsMap().get(operator), Operation.class);
				
		String firstValue = calc.getNumbersStack().pop();
		String secondValue = null;
		if (!operator.equals("undo") && !operator.equals("sqrt")) {
			secondValue = (calc.getNumbersStack().size() >= 1) ? calc.getNumbersStack().pop() : null;
		}
		
		String result = null;
		
		try {
			result = operation.execute(firstValue, secondValue);
		}catch(Exception e){
			calc.getNumbersStack().push(firstValue);
			throw e;
		}
		
		if (!operator.equals("undo") && !operator.equals("clear")) {			
			calc.getOperationStack().push(operation);
		}
		
		if (result != null) {
			calc.getNumbersStack().push(result);
		}					
	}
	
	/**
	 * Prints the content of the number values stack
	 */
	public void printStack() {
		
		if (!calc.getNumbersStack().isEmpty()) {
			System.out.print("stack: ");
			calc.getNumbersStack().stream().forEach(p -> System.out.print(Utils.formatNumber(Double.parseDouble(p)) + " "));
			System.out.println();
		}else {
			System.out.println("stack: ");
		}
	}
	
	/**
	 * Does string validations of the given command
	 * @param command
	 * @return
	 */
	private boolean checkCommand (String command) {
		if (command == null) {
			return false;
		}else if (command.trim().length() == 0) {
			return false;
		}else if (command.isEmpty()) {
			return false;
		}
		return true;
	}			

}
