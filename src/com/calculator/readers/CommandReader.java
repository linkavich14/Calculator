package com.calculator.readers;

import java.io.IOException;

/**
 * Interface used to add different types of entries for the calculator
 * @author Juan Hernandez
 *
 */
public interface CommandReader {
	
	/**
	 * Gets the next command from the input
	 * @return the next available command
	 * @throws IOException
	 */
	public String getFollowingCommand()  throws IOException;
	
	/**
	 * Terminates the reader, removes any instances or close flows
	 * @throws IOException
	 */
	public void finishReader() throws IOException;
	
}
