package com.calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.calculator.CalculatorRPN;
import com.calculator.config.SpringConfig;
import com.calculator.exceptions.CalculatorException;
import com.calculator.exceptions.InvalidOperationException;
import com.calculator.model.CalculatorBean;
import com.calculator.utils.Utils;

@RunWith(SpringRunner.class) 
@SpringBootTest
@ContextConfiguration(classes=SpringConfig.class)
public class CalculatorRPNTests {
	
	@Autowired
	private CalculatorRPN calculator;
	@Autowired
	private CalculatorBean calc;

	@Test
	public void additionTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("5 2 3 4 + + +");
		assertEquals("14", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 3 + 5 + 5 +");
		assertEquals("18", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 3 5 8 9 2 98 6");
		calculator.evaluateString("+ + + + + + +");
		assertEquals("136", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("-2 -3 +");
		assertEquals("-5", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
	}	
	
	@Test
	public void substractionTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("5 2 3 4 - - -");
		assertEquals("2", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 5 - 6 -");
		assertEquals("-6", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 2 -");
		assertEquals("3", calc.getNumbersStack().get(0));
		calculator.evaluateString("3 -");
		assertEquals("0", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 4 5 - 5 - 6 5 - - 6 - -");
		assertEquals("18", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("-2 -4 -");
		assertEquals("2", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
	}
	
	@Test
	public void multiplicationTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("1 2 5 * * 5 *");
		assertEquals("50", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 8 0 * 8 * 0 * *");
		assertEquals("0", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("-2 -5 * -3 5 * *");
		assertEquals("-150", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
	}
	
	@Test
	public void divisionTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("7 12 2 /");
		assertEquals("7", calc.getNumbersStack().get(0));
		assertEquals("6", calc.getNumbersStack().get(1));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("5 9 / 8 / 6 9 / /");
		assertEquals("0.1041666666666667", calc.getNumbersStack().get(0));		
		calculator.evaluateString("clear");
	}
	
	@Test
	public void sqrtTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("2 sqrt");
		assertEquals("1.4142135623730951", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear 9 sqrt");
		assertEquals("3", Utils.formatNumber(Double.parseDouble(calc.getNumbersStack().get(0))));
		calculator.evaluateString("clear");
		
		calculator.evaluateString("9 5 sqrt sqrt 6 sqrt sqrt sqrt");
		assertEquals("1.4953487812212205", calc.getNumbersStack().get(1));
	    assertEquals("1.2510334048590739", calc.getNumbersStack().get(2));
	    calculator.evaluateString("clear");
	}
	
	@Test
	public void undoTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("5 4 3 2");
		assertEquals(4, calc.getNumbersStack().size());
		calculator.evaluateString("undo undo *");
		assertEquals(1, calc.getNumbersStack().size());
        assertEquals("20", calc.getNumbersStack().get(0));
        calculator.evaluateString("5 *");
        assertEquals(1, calc.getNumbersStack().size());
        assertEquals("100", calc.getNumbersStack().get(0));
        calculator.evaluateString("undo");
        assertEquals(2, calc.getNumbersStack().size());
        assertEquals("20", calc.getNumbersStack().get(0));
        assertEquals("5", calc.getNumbersStack().get(1));
        calculator.evaluateString("clear");
	}
	
	@Test
	public void expressionTest() {
		calculator.evaluateString("clear");
		calculator.evaluateString("19 2.14 + 4.5 2 4.3 / - *");
		assertEquals("85.297441860465117164", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
	}
	
	@Test
	public void insuficientParametersTest() {
		calculator.evaluateString("clear");
		String exception = calculator.evaluateString("1 2 3 * 5 + * * 6 5");
		assertEquals(exception, "operator * (position: 8): insuficient parameters");
		assertEquals("11", calc.getNumbersStack().get(0));
		calculator.evaluateString("clear");
	}
	
	@Test (expected = InvalidOperationException.class)
	public void invalidOperatorTest() throws Exception {
		calculator.evaluateString("clear");
		calc.getNumbersStack().push("5");
		calculator.executeOperation("2+");		
		calculator.evaluateString("clear");
	}	
	
	@Test (expected = CalculatorException.class)
	public void removeSpaceParametersTest() throws Exception {
		calculator.evaluateString("clear");
		calculator.executeOperation("565*+5+");
	}	
	
	@Test (expected = CalculatorException.class)
	public void invalidParametersTest() throws Exception {
		calculator.evaluateString("clear");
		calculator.executeOperation("*");
	}
	
	@Test (expected = CalculatorException.class)
	public void nullParametersTest() throws Exception {	
		calculator.evaluateString("clear");
		calculator.executeOperation(null);		
	}
	
	@Test (expected = CalculatorException.class)
	public void emptyParametersTest() throws Exception {
		calculator.evaluateString("clear");
		calculator.executeOperation(" ");		
	}
	
	@Test (expected = ArithmeticException.class)
	public void divisionByZeroTest() throws Exception {
		calculator.evaluateString("clear");
		calc.getNumbersStack().push("0");
		calc.getNumbersStack().push("5");
		calculator.executeOperation("/");		
	}
}
