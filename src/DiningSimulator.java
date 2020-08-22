/**
 * The DiningSimulator class creates an instance of DiningSimulator object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 4 R09
 *
 */
import java.util.*;
public class DiningSimulator {
	ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	private int chefs;
	private int duration;
	private double arrivalProb;
	private int maxCustomerSize;
	private int numRestaurants;
	private int customersLost;
	private int totalServiceTime;
	private int customersServed = 0;
	private int profit,customerNum=1,price,timeToServe;
	private String done = "";
	
/**
 * Sets variable chefs to chef
 * @param chef
 * 		the value to be assigned to chefs
 */
	public void setChefs(int chef) {
		this.chefs = chef;
	}

/**
 * Sets variable duration to duration
 * @param duration
 * 		the value to be assigned to variable duration
 * @throws IllegalSimulationException
 * 		thrown when duration is less than 0
 */
	public void setDuration(int duration) throws IllegalSimulationException{
		if(duration < 0) {
			throw new IllegalSimulationException();
		}else {
			this.duration = duration;
		}
	}

/**
 * Sets variable arrivalProb to prob
 * @param prob
 * 		the value to be assigned to variable arrivalProb
 * @throws IllegalArrivalProbException
 * 		thrown when prob is less than 0
 */
	public void setArrivalProb(double prob) throws IllegalArrivalProbException {
		if(prob < 0) {
			throw new IllegalArrivalProbException();
		}else {
			this.arrivalProb = prob;
		}
	}

/**
 * Sets variable numRestaurants to numR
 * @param numR
 * 		the value to be assigned to variable numRestaurants
 * @throws IllegalNumResException
 * 		thrown when numR is less than 0
 */
	public void setNumRestaurants(int numR) throws IllegalNumResException{
		if(numR < 0) {
			throw new IllegalNumResException();
		}else {
			this.numRestaurants = numR;
		}
	}

/**
 * Sets variable maxCustomerSize to max
 * @param max
 * 		the value to be assigned to variable maxCustomerSize
 * @throws IllegalMaxException
 * 		thrown when max is less than 0
 */
	public void setMaxCustomerSize(int max) throws IllegalMaxException{
		if(max < 0) {
			throw new IllegalMaxException();
		}else {
			this.maxCustomerSize = max;
		}
	}
/**
 * Calculates the average time the customer spent at the restaurant	
 * @return
 * 		The average time the customer spent at the restaurant	
 * @throws NoCustomerServedException
 * 		Thrown when no customers were served
 */
	public double simulate() throws NoCustomerServedException {
		  if(customersServed == 0) {
			  throw new NoCustomerServedException();
		  }
		  return Math.round(((double)totalServiceTime/customersServed)*100.0)/100.0;
	  }
/**
 * Checks if the randomly generated number is less than or equal to arrivalProb
 * @return
 * 		A boolean value of whether the randomly generated number is less than or equal to arrivalProb
 */
	private boolean occurs() {
		return (Math.random() <= this.arrivalProb);
	}
/**
 * Generates a random Integer between minValue and maxValue
 * @param minValue
 * 		The minimum bound of the integer
 * @param maxValue
 * 		The maximum bound of the integer
 * @return
 * 		A randomly generated integer within bounds
 */
	private int randInt(int minValue, int maxValue) {
		return minValue + (int)(Math.random()*maxValue-minValue+1);
	}

/**
 * Randomly assigns a type of food to the food variable
 * @return
 * 		The food chosen
 */
	public String food() {
		int num = this.randInt(1, 5);
		int changeInTime = (this.chefs > 5) ? -10 : -(this.chefs - 3) * 5;
		String food ="";
		if(num == 1) {
			food = "CT";
			price = 10;
			timeToServe = 25 + changeInTime + 15;
		}else if(num ==2) {
			food = "C";
			price = 15;
			timeToServe = 25 + changeInTime + 15;
		}else if(num == 3) {
			food = "S";
			price = 25;
			timeToServe = 30 + changeInTime + 15;
		}else if (num == 4) {
			food = "GC";
			price = 10;
			timeToServe = 15 + changeInTime + 15;
		}else if(num == 5){
			food = "CW";
			price = 20;
			timeToServe = 30 + changeInTime + 15;
		}
		return food;
	}
/**
 * Determine whether a customer enters a particular restaurant and if there's space left in the restaurant
 * @param time
 * 		The simulation unit when the customer arrived
 */
	public void customersEntering(int time){
		String seated = "",food;
		System.out.print(done);
		for(int i = 0; i < this.numRestaurants; i++) {
			int roll = 3;
			while(roll > 0) {
				if(this.occurs()) {
					food = this.food();
					Customer c = new Customer(customerNum,food,price,time,timeToServe);
					System.out.println("Customer #" + c.getOrderNum() + " has entered Restaurant " + (i+1) + ".");
					if(this.restaurants.get(i).size() < this.maxCustomerSize) {	
						this.restaurants.get(i).enqueue(c);
						seated += "Customer #" + c.getOrderNum() + " has been seated with the order '" + c.getFood(true) + "'\n";
						customerNum++;
					}else {
						seated += "Customer #" + c.getOrderNum() + " cannot be seated! They have left the restaurant.\n";
						this.customersLost++;
						customerNum++;
					}
				}
				roll--;
			}
		}
		System.out.print(seated);
	}
	
/**
 * Updating the time left to serve a customer and remove the customer when the time reaches 0
 * @return
 * 		A string of customers done with their meals
 * @throws NoChefsException
 * 		Thrown when there's no chef available		
 */
	public String updating() throws NoChefsException{
		if(this.chefs < 1) {
			throw new NoChefsException();
		}
		done = "";
		for(int i = 0; i < restaurants.size(); i++) {
			for(int j = 0; j < restaurants.get(i).size(); j++) {
				if(restaurants.get(i).get(j).getTime() > 0) {
					restaurants.get(i).get(j).setTime(restaurants.get(i).get(j).getTime() - 5);
					if(restaurants.get(i).get(j).getTime() == 0) {
						Customer removed = restaurants.get(i).dequeue(j);
						done+= "Customer #" + removed.getOrderNum() + " has enjoyed their food! $" + removed.getPrice()+ " profit.\n";
						profit += removed.getPrice();
						this.customersServed++;
						this.totalServiceTime += removed.getOriginal();
						j--;
					}
				}
			}
		}
		return done;
	}
	
/**
 * Returns a string representation of DiningSimulator Object
 */
	public String toString() {
		String x = "";
		for(int i = 0; i < restaurants.size(); i++) {
			x += "R" + (i+1) + ": " + restaurants.get(i).toString();
			x += "\n";
		}
		return x;
	}

/**
 * Resets all the variables to default value and remove everything from the restaurants arraylist
 */
	public void reset() {
		restaurants.removeAll(restaurants);
		customersServed = 0;
		customersLost = 0;
		customerNum = 1;
		totalServiceTime = 0;
		profit = 0;
	}

/**
 * Acts as a test to test for all the classes and methods.
 * @param args
 */
	public static void main(String[] args) {
		DiningSimulator x = new DiningSimulator();
		Scanner stdin = new Scanner(System.in);
		boolean cont = false;
		do {
			try {
				System.out.println("Starting simulator...\n");
				System.out.print("Enter the number of restaurants: ");
				x.setNumRestaurants(stdin.nextInt()); 
				int numR = x.numRestaurants,time = 1;
				System.out.print("Enter the maximum number of customers a restaurant can serve: ");
				x.setMaxCustomerSize(stdin.nextInt());
				System.out.print("Enter the arrival probability of a customer: ");
				x.setArrivalProb(stdin.nextDouble());
				System.out.print("Enter the number of chefs: ");
				x.setChefs(stdin.nextInt());
				System.out.print("Enter the number of simulator units: ");
				x.setDuration(stdin.nextInt());
				System.out.println();
				while(numR > 0) {
					Restaurant r = new Restaurant();		
					x.restaurants.add(r);
					numR--;
				}
				while(x.duration > 0) {
					x.updating();
					System.out.println("Time: " + time);
					x.customersEntering(time);
					time++;
					x.duration--;
					System.out.println(x.toString());
				}
				System.out.println("Simulation ending...");
				System.out.print("\nTotal customer time: " + x.totalServiceTime + " minutes\nTotal customers served: " + x.customersServed + "\nAverage customer time lapse: "
				  + x.simulate() + " minutes per order\nTotal Profit: $" + x.profit + "\nCustomers that left: " + x.customersLost +"\n");

			}catch (NoCustomerServedException ex) {
				System.out.print("\nTotal customer time: " + x.totalServiceTime + "\nTotal customers served: " + x.customersServed  + "\nTotal Profit: $" + x.profit + "\nCustomers that left: " + x.customersLost +"\n");
				System.out.println(ex.getMessage());
			}catch(IllegalArrivalProbException ex2) {
				System.out.println(ex2.getMessage());
			}catch(IllegalMaxException ex3) {
				System.out.println(ex3.getMessage());
			}catch(IllegalNumResException ex4) {
				System.out.println(ex4.getMessage());
			}catch(IllegalSimulationException ex5) {
				System.out.println(ex5.getMessage());
			}catch(NoChefsException ex1){
				System.out.println(ex1.getMessage());
			}finally {
				System.out.println("\nDo you want to try another simulation?(y/n)");
				char next = stdin.next().charAt(0);
				if(Character.toUpperCase(next) == 'Y') {
					cont = true;
					x.reset();
				}else {
					cont = false;
					System.out.println("Program terminating...");
				}
			}
			}while(cont);
		stdin.close();
	}
}
