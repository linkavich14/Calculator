package com.calculator.operations;

/**
 * <p>Interface used to determine the type of operation to execute, such as
 * 	  Add, Clear, Divide, Multiply, SQRT, Subtract or Undo.
 *    It will contain a history of a successful operation, except for Clear and Undo operations		
 * </p>
 * @author Juan Hernandez
 */
public interface Operation {
	/**
	 * Does the calculations required to complete the operation 
	 * and handles the mathematical cases for each operation.
	 * @param firstValue the first value required for the calculation, can be null for some operations
	 * @param secondValue the second value required for the calculation, can be null for some operations
	 * @return null or the result of the operation
	 * @throws Exception when invalid types, values are entered
	 */
	public String execute(String firstValue, String secondValue) throws Exception;
	
	/**
	 * Gets the first value of a successful operation
	 * @return
	 */
	public String getFirstValue();
	
	/**
	 * Gets the second value of a successful operation
	 * @return
	 */
	public String getSecondValue();
	
}
