package com.calculator.operations;

import java.math.BigDecimal;
import java.math.MathContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.InsufficientParametersException;
import com.calculator.utils.Utils;

/**
 * <p>This operation takes care of the division of real numbers. 
 * 	  If the values are accepted, the class will return 
 * 	  the result of the operation.</p>
 * @author Juan Hernandez 
 */
@Component("Divide")
@Scope("prototype")
public class DivideOperation implements Operation{
	
	private String firstValue;
	private String secondValue;

	/**
	 * Returns the result of the division
	 */
	@Override
	public String execute(String firstValue, String secondValue) throws Exception {				
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		BigDecimal value1 = Utils.getBigDecimalFromString(firstValue);
		BigDecimal value2 = null;
		
		if (secondValue != null) {
			value2 = Utils.getBigDecimalFromString(secondValue);
			if (value2.equals(new BigDecimal("0"))) {
				throw new ArithmeticException("Cannot divide by 0");
			}
		} else {
			throw new InsufficientParametersException("/");
		}			
		return String.valueOf(value2.divide(value1, MathContext.DECIMAL64));
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
