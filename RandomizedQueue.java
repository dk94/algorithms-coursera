import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node last;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    public RandomizedQueue() {
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (isEmpty()) {
            last = newNode;
        } else {
            Node oldLast = last;
            last = newNode;
            oldLast.next = last;
            last.previous = oldLast;
        }

        size++;
    }

    public Item dequeue() {
        if (!isEmpty()) {
            int random = StdRandom.uniform(size());
            int count = 0;
            Node current = last;

            while (count < random) {
                current = current.previous;
                count++;
            }

            if (size() == 1) {
                last = null;
                size--;

                return current.item;
            } else if (current.next == null) {
                last = current.previous;
                current.previous.next = null;
                size--;

                return current.item;
            } else if (current.previous == null) {
                current.next.previous = null;
                size--;

                return current.item;
            } else {
                Node next = current.next;
                next.previous = current.previous;
                current.previous.next = next;
                size--;

                return current.item;
            }

        } else {
            throw new NoSuchElementException();
        }
    }

    public Item sample() {
        if (!isEmpty()) {
            int random = StdRandom.uniform(size());
            int count = 0;
            Node current = last;

            while (count < random) {
                current = current.previous;
                count++;
            }

            return current.item;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        int[] nums = new int[size()];
        int count = 0;

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int random = 1;
            int count2 = 1;
            Node current = last;
            boolean condition = true;
            while (condition) {
                random = StdRandom.uniform(size()) + 1;
                boolean found = false;
                for (int k = 0; k < nums.length; k++) {
                    if (nums[k] == random) {
                        found = true;
                    }
                }

                if (!found) {
                    nums[count] = random;
                    condition = false;
                }
            }

            while (count2 < random) {
                current = current.previous;
                count2++;
            }

            count++;

            return current.item;

        }

        public boolean hasNext() {
            return size != count;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
    }
}