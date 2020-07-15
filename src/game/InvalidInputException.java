package game;

/**
 * The InvalidInputException class is a custom exception that extends the Exception class.
 * This is to be thrown when there is an invalid input.
 * 
 * Author: Linda Fung
 * Date: 05-17-2020
 * Course: CS622
 */

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidInputException(String message) {
		super(message);
	}
	
}