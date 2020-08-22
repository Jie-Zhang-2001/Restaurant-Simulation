/**
 * The Customer class creates an instance of Customer object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 4 R09
 *
 */
public class Customer {
	private static int totalCustomers;
	private int orderNumber;
	private String food;
	private int priceOfFood;
	private int timeArrived;
	private int timeToServe;
	private int originalTime;
	
/**
 * No-arg constructor for the Customer class
 * creates an instance of Customer object
 */
	public Customer() {
		
	}
/**
 * Creates an instance of Customer object
 * @param customerNum
 * 		The number assigned to an entering customer
 * @param food
 * 		The food randomly chosen for the customer
 * @param price
 * 		The price of the food chosen
 * @param timeArrived
 * 		The simulation unit when the customer arrived
 * @param timeToServe
 * 		The amount of time required to serve a customer
 */
	public Customer(int customerNum, String food,int price,int timeArrived,int timeToServe) {
		this.originalTime = timeToServe;
		this.food = food;
		this.priceOfFood = price;
		this.orderNumber = customerNum;
		this.timeArrived = timeArrived;
		this.timeToServe = timeToServe;
		
	}

/**
 * Returns the value of originalTime
 * @return
 * 		The value of originalTime
 */
	public int getOriginal() {
		return originalTime;
	}
/**
 * Returns the value of orderNumber
 * @return
 * 		The value of orderNumber
 */
	public int getOrderNum() {
		return this.orderNumber;
	}
	
/**
 * Returns the reference of food
 * @param translate
 * 		Whether to abbreviate the food or not
 * @return
 * 		The reference of food
 */
	public String getFood(boolean translate) {
		String foodName = food;;
		if(translate) {
			if(food.equals("CW")) {
				foodName = "Chicken Wings";
			}
			if(food.equals("CT")) {
				foodName = "Chicken Tenders";
			}
			if(food.equals("S")) {
				foodName = "Steak";
			}
			if(food.equals("GC")) {
				foodName = "Grilled Cheese";
			}
			if(food.equals("C")) {
				foodName = "Cheese Burger";
			}
		}
		return foodName;
	}
	
/**
 * Returns the value of timeToServe
 * @return
 * 		The value of timeToServe
 */
	public int getTime() {
		return timeToServe;
	}
	
/**
 * Returns the value of priceOfFood
 * @return
 * 		The value of priceOfFood
 */
	public int getPrice() {
		return priceOfFood;
	}
	
/**
 * Sets timeToServe to num
 * @param num
 * 		The integer to be assigned to timeToServe
 */
	public void setTime(int num) {
		timeToServe = num;
	}
/**
 * Returns a string representation of the customer object
 */
	public String toString() {
		return "[#" + this.orderNumber + ", " + this.food + ", " + this.timeToServe +" min.]";
	}
}
