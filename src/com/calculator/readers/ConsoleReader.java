package com.calculator.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

/**
 * Reader used to read commands from the console
 * @author Juan Hernandez
 *
 */
@Component
public class ConsoleReader implements CommandReader{
	
	BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public String getFollowingCommand() throws IOException {		
		return consoleReader.readLine();
	}

	@Override
	public void finishReader() throws IOException {		
		consoleReader.close();
	}

}
