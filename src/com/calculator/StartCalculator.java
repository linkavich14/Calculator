package com.calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.calculator.config.SpringConfig;

/**
 * <p>This class starts the calculator application</p>
 * @author Juan Hernandez
 */
public class StartCalculator {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		CalculatorRPN cal = context.getBean("calculatorRPN", CalculatorRPN.class);
		cal.initCommandReader();
		context.close();
	}
}
