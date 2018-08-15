package com.calculator.operations;

import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.InsufficientParametersException;
import com.calculator.utils.Utils;

/**
 * <p>This operation class takes care of subtracting two numbers, 
 * 	  accepts all real numbers. If the values are accepted, the class will return 
 * 	  the result</p>
 * @author Juan Hernandez
 */
@Component("Substract")
@Scope("prototype")
public class SubtractOperation implements Operation{
	
	private String firstValue;
	private String secondValue;
	
	/**
	 * Returns the result of subtraction
	 */
	@Override
	public String execute(String firstValue, String secondValue) throws Exception {
		this.firstValue = firstValue;
		this.secondValue = secondValue;		
		BigDecimal value1 = Utils.getBigDecimalFromString(firstValue);
		BigDecimal value2 = null;	
				
		if (secondValue != null) {
			value2 = Utils.getBigDecimalFromString(secondValue);
		}else {
			throw new InsufficientParametersException("-");
		}				
		return String.valueOf(value2.subtract(value1));
	}

	@Override
	public String getFirstValue() {
		return firstValue;
	}

	@Override
	public String getSecondValue() {
		return secondValue;
	}

}
