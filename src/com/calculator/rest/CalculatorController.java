package com.calculator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.CalculatorRPN;
import com.calculator.model.CalculatorBean;

@RestController
@RequestMapping("/rpncalculator")
public class CalculatorController {
	@Autowired
	private CalculatorRPN calculator;
	@Autowired
	private CalculatorBean calc;

	@GetMapping("/calculateOperation/{input}")
	public CalculatorBean calculateOperation(@PathVariable String input) {
		calculator.evaluateString(input);
		return calc;
	}

}
