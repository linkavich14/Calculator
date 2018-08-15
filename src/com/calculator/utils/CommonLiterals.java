package com.calculator.utils;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <p>This class contains all the operations available and it is matched with its own symbol</p>
 * @author Juan Hernandez
 *
 */
@Component("commonLiterals")
@Scope("singleton")
public class CommonLiterals {
	
	private Map<String, String> operationsMap;
	
	public CommonLiterals() {
		operationsMap = new TreeMap<>();
		operationsMap.put("+", "Add");
		operationsMap.put("-", "Substract");
		operationsMap.put("*", "Multiply");
		operationsMap.put("/", "Divide");
		operationsMap.put("sqrt", "Sqrt");
		operationsMap.put("clear", "Clear");
		operationsMap.put("undo", "Undo");
	}
	
	/**
	 * Gets the map used to retrieve the class that will execute the operation
	 * @return 
	 */
	public Map<String, String> getOperationsMap(){
		return operationsMap;
	}

}
