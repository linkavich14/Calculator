package com.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.calculator.model.CalculatorBean;

/**
 * Spring configuration class to make use of spring to get the required operations via injection
 * @author Juan Hernandez
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.calculator")
@ComponentScan("com.calculator.utils")
@ComponentScan("com.calculator.operations")
public class SpringConfig {
	
	@Bean
	//@Scope(value = WebApplicationContext.SCOPE_SESSION, 
	//	   proxyMode = ScopedProxyMode.TARGET_CLASS)
	public CalculatorBean calculatorBean() {
		return new CalculatorBean();
	}
}
