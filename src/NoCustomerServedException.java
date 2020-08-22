/**
 * The NoCustomerServedException class creates an instance of NoCustomerServedException object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 4 R09
 *
 */
public class NoCustomerServedException extends Exception{
	public NoCustomerServedException() {
		super("\nNo Customer Served! No average wait time!");
	}
}
