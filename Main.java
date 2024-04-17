import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//Declares variables
		int currentDays = 1;
		int chicksInStock = 0; //Number of chicks currently in stock
		double avgTime = 0; //Average amount of time that chicks stayed in the warehouse
		double avgPriceNum = 0; //Average price of the chicks sold
		ArrayList<Order> completedOrders = new ArrayList<>(); //List of completed orders. 
		ArrayList<Shipment> completedShipments = new ArrayList<>(); //List of completed shipments.
		ArrayList<Integer> daysInWare = new ArrayList<>(); //Records the days that a chick stayed in the warehouse.
		ArrayList<Double> avgPrice = new ArrayList<>(); //Average price per chick
		Stack stack = new Stack(); //Stack to store shipments
		Queue queue = new Queue(); //Queue to store orders
		
		//Asks the user to put in the input file name
		Scanner scan = new Scanner(System.in); //Command line scanner
		System.out.println("Please put in the file input name: ");
		String inFile = scan.nextLine();
		Scanner input = new Scanner(new File(inFile)); //Input file
		//IMPORTANT: The input file will only work if there are no extra spaces after each line.
		
		//Asks user to put in output file name
		System.out.println("Please put in the file output name: ");
		String outFile = scan.nextLine();
		PrintStream output = new PrintStream(new File(outFile)); //Output file
		
		//Puts values from the input file into queue or stack, by creating objects out of the lines.
		while(input.hasNextLine()) {
			String current = input.nextLine();
			String[] tokens = current.split(", ");
			
			//Evaluates the input if it is an order or a shipment.
			if(tokens[0].equals("O")) {
				//Get variables from record
				String name = tokens[1];
				int chicks = Integer.parseInt(tokens[2]);
				int days = Integer.parseInt(tokens[3]);
				
				//Add days
				currentDays += days;
				
				//Create Order and push it
				Order order = new Order(name, chicks, currentDays);
				queue.push(order);
			} else if (tokens[0].equals("S")) {
				//Get variables from record
				int chicks = Integer.parseInt(tokens[1]);
				double price = Double.parseDouble(tokens[2]);
				int days = Integer.parseInt(tokens[3]);
				
				//Add chicks / days
				chicksInStock += chicks;
				currentDays += days;
				
				//Create shipment and push it
				Shipment ship = new Shipment(chicks, price, currentDays);
				stack.push(ship);
			}
						
			//Processes chickens to orders, if there is at least one shipment and one order in. This is because you cannot process one without another.
			if(stack.size() > 0 && queue.size() > 0) {
				//Find the current order. It finds this first in order to get the needed chicks. 
				Order currentOrder = queue.peek();
				int chicksNeeded = currentOrder.getChicks();	
				
				/* If the amount of chicks in stock is bigger than the amount of chicks needed, the order can be fulfilled. 
				 * This is because the current order can only be processed when it has all the chicks needed. The chicksNeeded should
				 * also be bigger than 0 because that way, more orders can be processed, and stack should be bigger than 0 to prevent errors.
				 */
					while(chicksNeeded > 0 && stack.size() > 0 && chicksInStock >= chicksNeeded) {
						//Finds current shipment and current chicks in that shipment
						Shipment currentShipment = stack.peek();
						int currentChicks = currentShipment.getChicks();
						
						
						//Evaluates the totals differently depending on the chicks in the current order and shipment. 
						if(chicksNeeded > currentChicks) {
							//Subtracts the amount of chicks from the shipment.
							currentOrder.subChicks(currentChicks);
							currentShipment.subChicks(currentChicks);
							chicksInStock -= currentChicks;
							
							//Adds totals based on how many days have passed. 
							currentOrder.addTotal(currentChicks * (currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays()))));
							
							//Adds days to the days in warehouse list per chick sold
							for(int i = 0; i < currentChicks; i++) {
								//It takes the difference because we only want the days that have passed since they were put into the warehouse
								daysInWare.add(currentDays-currentShipment.getDays());
							}
							
							//Adds up prices to avg price list.
							for(int i = 0; i < currentChicks; i++) {
								avgPrice.add(currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays())));
							}
					
							completedShipments.add(stack.pop()); //the shipment is completed;
				
						} else if (chicksNeeded < currentChicks) {
							//Subtracts the amount of chicks from the order.
							currentOrder.subChicks(chicksNeeded);
							currentShipment.subChicks(chicksNeeded);
							chicksInStock -= chicksNeeded;
							
							//Evaluates total based on the shipment price and how many days have passed. 
							currentOrder.addTotal(chicksNeeded * (currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays()))));
							
							//Puts the days per each chick into the daysInWare list
							for(int i = 0; i < chicksNeeded; i++) {
								//It takes the difference because we only want the days that have passed since they were put into the warehouse
								daysInWare.add(currentDays-currentShipment.getDays());
							}
							
							//Adds up prices to avgPrice list.
							for(int i = 0; i < chicksNeeded; i++) {
								avgPrice.add(currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays())));
							}
							
							completedOrders.add(queue.pop()); //the order is completed;
							
						} else if (chicksNeeded == currentChicks) {
							//Subtracts the chicks
							currentOrder.subChicks(chicksNeeded);
							currentShipment.subChicks(chicksNeeded);
							chicksInStock -= chicksNeeded;
							
							//Calculates total based on the shipment price and amount of days passed. 
							currentOrder.addTotal(currentChicks * (currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays()))));
							
							//Adds days to the daysInWare list per chick sold
							for(int i = 0; i < chicksNeeded; i++) {
								//It takes the difference because we only want the days that have passed since they were put into the warehouse
								daysInWare.add(currentDays-currentShipment.getDays());
							}
							
							//Adds up prices to avgPrice list.
							for(int i = 0; i < currentChicks; i++) {
								avgPrice.add(currentShipment.getPrice()+ (0.10 * (currentDays-currentShipment.getDays())));
							}
							
							completedOrders.add(queue.pop()); //the order is completed;
							completedShipments.add(stack.pop()); //the shipment is completed;
						}
						
						//Changes the currentOlder and currentShipment based on the current value in stack and queue.
						//These commands make sure the correct values are being accessed. 
						if(queue.size() > 0 && queue.peek().getChicks() <= chicksInStock) {
							currentOrder = queue.peek();
						}
						chicksNeeded = currentOrder.getChicks();
						
						if(stack.size()>0) {
							currentShipment = stack.peek();
						}
						currentChicks = currentShipment.getChicks();
						
				}
				
			}	
		}
		
		//Calculate the average days a chick spent in the warehouse
		for(int i = 0; i < daysInWare.size(); i++) {
			avgTime += daysInWare.get(i);
		}
		avgTime = avgTime/daysInWare.size();
		
		//Calculate the average price per chick
		for(int i = 0; i < avgPrice.size(); i++) {
			avgPriceNum += avgPrice.get(i);
		}
		avgPriceNum = avgPriceNum/avgPrice.size();
		
		//Gets variables for summary
		double total=0;
		int sold = 0;
		
		//Prints out summary information
		output.println("Company\t\t\t\t        Chicks Ordered\t\tPrice/Chick\t\tTotal Cost");
		output.println("----------------------------------------------------------------------------------------------------");
		//Prints out the amount of rows based on orders completed
		for(int i = 0; i < completedOrders.size(); i++) {
			//Get current completed order
			Order cur = completedOrders.get(i);
			double curAverage = (cur.getTotal()/cur.getChicksPurchased());
			
			//Has different printf statements for different lengths. This makes the formatting look better.
			if(cur.getName().length() < 16) {
				output.printf("%s\t\t\t\t%d\t\t\t%.2f\t\t\t%.2f\n", cur.getName(), cur.getChicksPurchased(), curAverage, cur.getTotal());
			} else if (cur.getName().length() > 25) {
				output.printf("%s\t\t%d\t\t\t%.2f\t\t\t%.2f\n", cur.getName(), cur.getChicksPurchased(), curAverage, cur.getTotal());
			} else {
				output.printf("%s\t\t\t%d\t\t\t%.2f\t\t\t%.2f\n", cur.getName(), cur.getChicksPurchased(), curAverage, cur.getTotal());
			}
			
			//Update variables
			total += cur.getTotal();
			sold += cur.getChicksPurchased();
		}
		output.println("----------------------------------------------------------------------------------------------------");
		
		output.println("\nSummary Statistics:\n");
		output.println("Total number of sales: " + completedOrders.size());
		output.println("Total number of chicks sold: " + sold);
		output.printf("Average price of each chick sold: %.2f\n",avgPriceNum);
		output.printf("Average number of days a sold chick stayed in the warehouse: %.2f\n", avgTime);
		output.printf("Gross sales total: %.2f", total);
		
		//Prints out this statement to the file if everything went well
		System.out.println("All sales from input written to output successfully.");
		
		//Closes all files
		scan.close();
		input.close();
		output.close();

	}
}