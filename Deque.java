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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.previous = first;
        }
        if (isEmpty()) {
            last = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;

        if (oldLast != null) {
            oldLast.next = last;
            last.previous = oldLast;
        } else {
            first = last;
        }
        size++;
    }

    public Item removeFirst() {
        if (size > 0) {
            Item item = first.item;
            first = first.next;

            size--;

            if (isEmpty()) {
                first = null;
                last = null;
            }
            return item;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Item removeLast() {
        if (last != null) {
            Item item = last.item;
            last = last.previous;
            if (last != null) {
                last.next = null;
            }

            size--;

            if (isEmpty()) {
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

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
    }
}
