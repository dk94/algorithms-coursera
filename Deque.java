import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int size;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	public Deque() {
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addFirst(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		if(size == 0) {
			last = first;
		}
		size++;
	}
	
	public String toString() {
		Node current = first;
		String str = "";
		while(current != null) {
			str += " " + current.item;
			current = current.next;
		}
		
		return str;
	}
	
	public void addLast(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
	
		if(oldLast != null) {
			oldLast.next = last;
			last.previous = oldLast;
		} else {
			first = last;
		}
		size++;
	}
	
	public Item removeFirst() {
		if(size > 0) {
			Item item = first.item;
			first = first.next;

			size--;
			
			if(size == 0) {
				first = null;
				last = null;
			}
			return item;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public Item removeLast() {
		if(last != null) {
			Item item = last.item;
			Node oldLast = last;
			last = last.previous;
			if(last != null) {
				last.next = null;
			}
			
			size--;
			
			if(size == 0) {
				first = null;
				last = null;
			}
			return item;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	
	
	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		Node current = first;
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public boolean hasNext() {
			return current.next != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		Deque deque = new Deque();
		
		deque.addFirst("Bohdan");
		deque.addFirst("Siavolt");
		deque.addLast("Aleksey");
		deque.addLast("Aleksey");
		deque.removeFirst();
		deque.removeLast();
		  for (Object s : deque) {
	            StdOut.print(s);
	        }
		
	}
}
