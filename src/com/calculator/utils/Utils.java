package com.calculator.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>This class contains the most common tools used around the project</p>
 * @author Juan Hernandez
 *
 */
public class Utils {
	
	/**
	 * Converts a String into a big decimal number
	 * @param value 
	 * 
	 */
	public static BigDecimal getBigDecimalFromString (String value){
		if (value == null) {
			throw new NumberFormatException("null string value");
		}

		value = value.replaceAll("\\s+", "");
		if (value.length() <= 0) {
			value = "0";
		}

		if (isNumber(value)) {
			return new BigDecimal(value);
		} else {
			throw new NumberFormatException();
		}								
	}
	
	/**
	 * Checks if a String is a valid real number 
	 */
	public static boolean isNumber(String value) {
		return value.matches("^[-+]?\\d+(\\.\\d+)?$");
	}
	
	/**
	 * Formats a number to 10 decimal places or less if there is no loss of precision.
	 * 	 
	 */
	public static String formatNumber(double value){		
		DecimalFormat numberFormat = new DecimalFormat("#.0000000000");
		double number = Double.parseDouble(numberFormat.format(value));
	    if(number == (long) number)
	        return String.format("%d",(long)number);
	    else
	        return String.format("%s",number);
	}

}
