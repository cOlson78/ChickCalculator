import java.util.*;

//This class is for an ArrayList implementation of Stack, which is used for shipments, in the context of this program. 
public class Stack {
	//Uses an ArrayList as a global variable.
	private ArrayList<Shipment> list;
	
	//Stack constructor. 
	public Stack() {
		ArrayList<Shipment> newList = new ArrayList<>();
		list = newList;
	}
	
	//Adds element to the list
	public void push(Shipment ship) {
		list.add(ship);
	}
	
	//Removes the last element added to the list, and returns it. This is how stacks work. (LIFO)
	public Shipment pop() {
		Shipment current = list.get(list.size()-1);
		list.remove(list.size()-1);
		return current;
	}
	
	//Returns the element at the top of the stack.
	public Shipment peek() {
		return list.get(list.size()-1);
	}
	
	//Returns size of the stack
	public int size() {
		return list.size();
	}
	
	//Gets the shipment at a certain index.
	public Shipment get(int i) {
		return list.get(i);
	}
		
	//Prints out all contents of the list
	public void print() {
		System.out.println(list.toString());
	}
}
