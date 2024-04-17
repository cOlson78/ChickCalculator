import java.util.*;

//This class is for an ArrayList implementation of Queue, which is used for orders, in the context of this program. 
public class Queue {
	//Uses an ArrayList as a global variable.
	private ArrayList<Order> list;
		
	//Stack constructor. 
	public Queue() {
		ArrayList<Order> newList = new ArrayList<>();
		list = newList;
	}
		
	//Adds element to the list
	public void push(Order order) {
		list.add(order);
	}
		
	//Removes the first element from the list, and returns it. This is how queues work. (FIFO)
	public Order pop() {
		Order current = list.get(0);
		list.remove(0);
		return current;
	}
		
	//Returns the element that is next in the queue.
	public Order peek() {
		return list.get(0);
	}
	
	//Returns size of the queue
	public int size() {
		return list.size();
	}
	
	//Gets the order at a certain index.
	public Order get(int i) {
		return list.get(i);
	}
		
	//Prints out all contents of the list
	public void print() {
		System.out.println(list.toString());
	}
}
