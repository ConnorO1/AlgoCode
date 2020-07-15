import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        private Node prev;
        private Item item;
        private Node next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element yo");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not okay - stop that now");
        }

    }


    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.prev = oldlast;
            last.next = null;
            oldlast.next = last;
        }
        size++;
    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No element yo11");
        }
        // need to check case that has one element
        else if (size == 1) {
            Item tmp = first.item;
            first = null;
            last = null;
            size--;
            return tmp;
        }
        else {
            Item tmp = first.item;
            first = first.next;
            first.prev = null;
            size--;
            return tmp;

        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No element yo33");
        }
        else if (size == 1) {
            Item tmp = last.item;
            last = null;
            first = null;
            size--;
            return tmp;
        }
        else {
            Item oldlast = last.item;
            last = last.prev;
            last.next = null;
            size--;
            return oldlast;
        }
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> f = new Deque<Integer>();
        f.addLast(1);
        f.addLast(2);

        for (Integer ff : f) {
            System.out.println(ff);
        }

    }


}



