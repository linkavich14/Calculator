package com.calculator.exceptions;

/**
 * <p>Exception used for users error when entering parameters</p>
 * @author Juan Hernandez
 */
public class InsufficientParametersException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public InsufficientParametersException(String message) {
		super(message);
	}
}
