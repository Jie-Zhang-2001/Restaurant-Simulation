/**
 * The Restaurant class creates an instance of Restaurant object
 * extending to LinkedList
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 4 R09
 *
 */
import java.util.LinkedList;
public class Restaurant extends LinkedList<Customer> {
	
/**
 * No-arg constructor of Restaurant class
 * Creates an instance of Restaurant object
 */
	public Restaurant() {
		
	}

/**
 * Adding a Customer object to this Restaurant object 
 * @param c
 * 		The Customer object to be added
*/
	public void enqueue(Customer c) {
		this.add(c);
	}
	
/**
 * Removing a Customer object from this Restaurant object 
 * @param i
 * 		The index of Customer object to be removed
 * @return
 * 		The reference of Customer object removed
*/
	public Customer dequeue(int i) {
		return this.remove(i);
	}
	
/**
 * Returns a Customer object reference from the end of this Restaurant object 
*/
	public Customer peek() {
		return this.peek();
	}

/**
 * Returns a string representation of this Restaurant object
 */
	public String toString() {
		String x ="{";
		if(this.isEmpty()) {
			x += "}";
		}else {
			for(int i = 0; i < this.size(); i++) {
				if(i != this.size()-1) {
					x += this.get(i).toString() + ", ";
				}else {
					x += this.get(i).toString() + "}";
				}
			}
		}
			return x;
		
	}
}
