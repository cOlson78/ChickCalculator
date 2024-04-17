
//This class contains shipment information and methods. 
public class Shipment {
	//Variables
	private int chicks; //Chicks in the shipment
	private double price; //Initial price per chick
	private int days; //Days since last event, or stored in warehouse
	
	//Shipment constructor
	public Shipment(int chicks, double price, int days) {
		this.chicks = chicks;
		this.price = price;
		this.days = days;
	}
	
	//This method subtracts the amount of chickens that were used for other orders.
	public void subChicks(int amount) {
		chicks -= amount;
	}
	
	//This method adds days that the chicks were in inventory. 
	public void addDays(int amount) {
		days += amount;
	}
	
	//toString method for printing out Order in string format.
	public String toString() {
		return "Shipment Chicks: " + chicks + " Price: " + price + ", Days: " + days;
	}
	
	//[Getter and setter methods]
	
	//Returns the number of chicks in the shipment
	public int getChicks() {
		return chicks;
	}
	
	//Returns the initial price per chicken in the shipment
	public double getPrice() {
		return price;
	}
		
	//Returns the days since last event when the shipment was processed
	public int getDays() {
		return days;
	}
	
	//Changes the number of chicks in the shipment
	public void setChicks(int chicks) {
		this.chicks = chicks;
	}
		
	//Sets the initial price per chicken in the shipment
	public void setPrice(double price) {
		this.price = price;
	}
			
	//Sets the days since last event when the shipment was processed
	public void setDays(int days) {
		this.days = days;
	}
}
