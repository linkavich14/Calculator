#RPN Calculator

Command line base calculator that takes the values from a stack in which operators follow their operands.
This program requires access to the Internet so maven can get all the necessary dependencies.

#Structure

	src\
	..\com\calculator\
						..\CalculatorRPN.java
						..\StartCalculator.java
	..\com\calculator\config\
						..\	SpringConfig.java
	..\com\calculator\exceptions\
						..\CalculatorException.java
						..\InsufficientParametersException.java
						..\InvalidOperationException.java
	..\com\calculator\operations\
						..\AddOperation.java
						..\ClearOperation.java
						..\DivideOperation.java
						..\MultiplyOperation.java
						..\Operation.java
						..\SQRT_Operation.java
						..\SubtractOperation.java
						..\UndoOperation.java
	..\com	\calculator\readers\
						..\CommandReader.java
						..\ConsoleReader.java
	..\com\calculator\utils\
						..\CommonLiterals.java
						..\Utils.java							   											

#Tests
The tests files are located:

	test\
	..\com\calculator\
						..\CalculatorRPNTest.java
	..\com\calculator\operations\
						..\OperationTests.java					

#How to start the application


To run it using maven, go to the directory of the project in the console and write the following commands:

	mvn compile

	mvn exec:java -Dexec.mainClass="com.calculator.StartCalculator"


Or can be run using an IDE:

-Run the com.calculator.StartCalculator.java as a Java Application
