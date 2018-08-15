package com.calculator.model;

import java.util.Stack;

import com.calculator.operations.Operation;

public class CalculatorBean {
	
	private Stack <Operation> operationStack = new Stack<>();
	private Stack <String> numbersStack = new Stack<>();
	private String exceptionmessage;
	
	public String getMessage() {
		return exceptionmessage;
	}
	
	public void setExceptionMessage(String message) {
		this.exceptionmessage = message;
	}
	
	public Stack<Operation> getOperationStack() {
		return operationStack;
	}
	
	public Stack<String> getNumbersStack() {
		return numbersStack;
	}
	
	
	

}
