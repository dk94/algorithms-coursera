package exercises;
import StdIn;
import StdOut;

public class LinkedStackOfStrings {
	 private Node first = null;
	 private class Node
	 {
	 String item;
	 Node next;
	 }

	 public boolean isEmpty()
	 { return first == null; }
	 public void push(String item)
	 {
	 Node oldfirst = first;
	 first = new Node();
	 first.item = item;
	 first.next = oldfirst;
	 }
	 public String pop()
	 {
	 String item = first.item;
	 first = first.next;
	 return item;
	 }
	 
	 public static void main(String[] args) {
	        int max = 10;
	        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(max);
	        while (!StdIn.isEmpty()) {
	            String item = StdIn.readString();
	            if (!item.equals("-")) stack.push(item); 
	            else if (stack.isEmpty())  StdOut.println("BAD INPUT"); 
	            else                       StdOut.print(stack.pop() + " ");
	        }
	        StdOut.println();

	        // print what's left on the stack
	        StdOut.print("Left on stack: ");
	        for (String s : stack) {
	            StdOut.print(s + " ");
	        }
	        StdOut.println();
	    } 
	}
