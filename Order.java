
//This class contains order information and methods.
public class Order {
	//Variables
	private String name; //Customer name
	private int chicksPurchased; //The number of chicks purchased. (this value doesn't decrease like chicks, used for summary information)
	private int chicks; //Chicks needed
	private int days; //The day that the order was processed.
	private double total; //Total cost of the order.
	
	//Order constructor
	public Order(String name, int chicks, int days) {
		this.name = name;
		this.chicks = chicks;
		this.days = days;
		total = 0; //Total cost always starts at 0
		chicksPurchased = chicks; //This value is the same. 
	}
	
	//This method add a specified amount to the total. 
	public void addTotal(double amount) {
		total += amount;
	}
	
	//This method subtracts the amount of chickens they still need for their order.
	public void subChicks(int amount) {
		chicks -= amount;
	}
	
	//This method resets the amount of chicks to original chicks purchased.
	public void resetChicks() {
		chicks = chicksPurchased;
	}
	
	//toString method for printing out Order in string format.
	public String toString() {
		return "Order Name: " + name + ", Chicks: " + chicks + " Days: " + days + ", Total: " + total;
	}
	
	//[Getter and setter methods]
		
	//Returns the name of the customer
	public String getName() {
		return name;
	}
	
	//Returns the number of chicks ordered
	public int getChicks() {
		return chicks;
	}
		
	//Returns the days since last event
	public int getDays() {
		return days;
	}
		
	//Returns the total cost of the order
	public double getTotal() {
		return total;
	}
	
	//Returns the amount of chicks purchased
	public int getChicksPurchased() {
		return chicksPurchased;
	}
	
	//Sets the name of the customer
	public void setName(String name) {
		this.name = name;
	}
		
	//Sets the number of chicks ordered
	public void setChicks(int chicks) {
		this.chicks = chicks;
	}
			
	//Sets the days since last event
	public void getDays(int days) {
		this.days = days;
	}
			
	//Sets the total cost of the order
	public void setTotal(double total) {
		this.total = total;
	}
	
	//Sets the chicks purchased.
	public void setChicksPurchased(int chicksPurchased) {
		this.chicksPurchased = chicksPurchased;
	}
}
