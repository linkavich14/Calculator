package com.calculator.operations;

import java.math.BigDecimal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.calculator.exceptions.CalculatorException;
import com.calculator.utils.Utils;

/**
 * <p>This operation class takes care of the square root of given real number,
 * 	  it does not accept imaginary numbers. If the values are accepted, the class will return 
 * 	  the result of the operation.</p>
 * @author Juan Hernandez
 */
@Component("Sqrt")
@Scope("prototype")
public class SQRT_Operation implements Operation{	
	private String firstValue;
	
	/**
	 * Returns the result of the square root of the first value, 
	 * second value is never used and is not necessary for this calculation
	 */
	@Override
	public String execute(String firstValue, String secondValue) throws Exception {
		this.firstValue = firstValue;		
		BigDecimal value = Utils.getBigDecimalFromString(firstValue);
		if (value.compareTo(BigDecimal.ZERO) < 0) {
			throw new CalculatorException("Imaginary numbers are not accepted");
		}
		
		return String.valueOf(Math.sqrt(value.doubleValue()));
	}

	@Override
	public String getFirstValue() {
		return firstValue;
	}
	
	/**
	 * It does not exist for this operation
	 */
	@Override
	public String getSecondValue() {
		return null;
	}

}
