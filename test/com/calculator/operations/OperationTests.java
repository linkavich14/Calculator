package com.calculator.operations;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.calculator.config.SpringConfig;
import com.calculator.exceptions.CalculatorException;
import com.calculator.model.CalculatorBean;

@RunWith(SpringRunner.class) 
@SpringBootTest
@ContextConfiguration(classes=SpringConfig.class)
public class OperationTests {
	
	@Autowired
	private CalculatorBean calc;
	@Autowired
	private AddOperation add;
	@Autowired
	private ClearOperation clear;
	@Autowired
	private DivideOperation divide;
	@Autowired
	private MultiplyOperation multiply;
	@Autowired
	private SQRT_Operation sqrt;
	@Autowired
	private SubtractOperation substract;
	@Autowired
	private UndoOperation undo;
	
	@Test
	public void addOperationTest() throws Exception {	
		clear.execute(null, null);
		assertEquals("3", add.execute("1", "2"));
		assertEquals("2", add.execute("0", "2"));
		assertEquals("-3", add.execute("-1", "-2"));
		assertEquals("4.09894", add.execute("1.5", "2.59894"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void addInvalidOperationTest() throws Exception {
		clear.execute(null, null);
		add.execute("ds", "d");		
	}
	
	@Test
	public void substractOperationTest() throws Exception{	
		clear.execute(null, null);
		assertEquals("1", substract.execute("1", "2"));
		assertEquals("-2", substract.execute("2", "0"));
		assertEquals("0", substract.execute("-2", "-2"));
		assertEquals("0.570507465345003", substract.execute("0.684987484654987", "1.25549494999999"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void substractInvalidOperationTest() throws Exception {
		clear.execute(null, null);
		substract.execute("ds", "d");	
	}
	
	@Test
	public void divideOperationTest() throws Exception {
		clear.execute(null, null);
		assertEquals("2", divide.execute("3", "6"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void divideInvalidOperationTest() throws Exception {
		clear.execute(null, null);
		divide.execute("rghe", "hrt");
	}
	
	@Test
	public void multiplyOperationTest() throws Exception {
		clear.execute(null, null);
		assertEquals("2", multiply.execute("1", "2"));
		assertEquals("0.08", multiply.execute("0.2", "0.4"));
		assertEquals("4", multiply.execute("-2", "-2"));
	}
	
	@Test (expected = NumberFormatException.class)
	public void multiplyInvalidOperationTest() throws Exception {
		clear.execute(null, null);
		multiply.execute("fdgfd", "gdfgfd");
	}
	
	@Test
	public void sqrtOperationTest() throws Exception {
		clear.execute(null, null);
		assertEquals("3.0", sqrt.execute("9", null));
		assertEquals("2.280350850198276", sqrt.execute("5.2", null));
		
	}
	
	@Test(expected=CalculatorException.class)
	public void sqrtImaginaryNumbersTest() throws Exception {
		clear.execute(null, null);
		assertEquals("3.0", sqrt.execute("-9", null));
	}
	
	@Test
	public void clearOperationTest() throws Exception {
		clear.execute(null, null);
		calc.getNumbersStack().push("1");
		calc.getNumbersStack().push("2");
		assertEquals(2, calc.getNumbersStack().size());
		clear.execute(null, null);
		assertEquals(0, calc.getNumbersStack().size());
	}
	
	/**
	 * Tests only the class to undo operations, not numerical inputs 
	 */
	@Test
	public void undoOperationTest() throws Exception {
		clear.execute(null, null);
		calc.getNumbersStack().push("1");
		calc.getNumbersStack().push("2");
		calc.getNumbersStack().push("3");
		calc.getNumbersStack().push("4");
		calc.getNumbersStack().push("5");
		assertEquals(5, calc.getNumbersStack().size());
		String sqrtResult = sqrt.execute(calc.getNumbersStack().pop(), null);
		calc.getNumbersStack().push(sqrtResult);
		calc.getOperationStack().push(sqrt);
		assertEquals("2.23606797749979", calc.getNumbersStack().get(4));
		calc.getNumbersStack().pop();
		undo.execute(null, null);
		assertEquals("5", calc.getNumbersStack().get(4));
		
	}

}
