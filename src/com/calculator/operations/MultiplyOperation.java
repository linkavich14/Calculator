package com.calculator.operations;

import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.InsufficientParametersException;
import com.calculator.utils.Utils;

/**
 * <p>This operation takes care of the multiplication of real numbers. 
 * 	  If the values are accepted, the class will return 
 * 	  the result of the operation.</p>
 * @author Juan Hernandez
 */
@Component("Multiply")
@Scope("prototype")
public class MultiplyOperation implements Operation{
	private String firstValue;
	private String secondValue;
	
	/**
	 * Returns the result of the multiplication
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
			throw new InsufficientParametersException("*");
		}				
		 			
		return String.valueOf(value1.multiply(value2));
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
