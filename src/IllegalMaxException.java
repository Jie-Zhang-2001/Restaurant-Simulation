/**
 * The IllegalMaxException class creates an instance of IllegalMaxException object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 4 R09
 *
 */
public class IllegalMaxException extends Exception{
	public IllegalMaxException() {
		super("\nInvalid max customer size!");
	}
}
