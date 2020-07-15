import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    // this will implement resizing array so that sample and
    private int size = 0;
    private Item[] s;
    private int capacity = 1;

    // new random iterator

    private class RndIterator<IItem> implements Iterator<IItem> {

        private int current = 0;
        private IItem[] copy;

        private RndIterator() {
            if (size != 0) {
                copy = (IItem[]) new Object[size];
                for (int i = 0; i < size; i++) {
                    copy[i] = (IItem) s[i];
                }
                StdRandom.shuffle(copy, 0, size - 1);
            }
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public IItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("...");
            }
            IItem tmp = copy[current];
            current++;
            return tmp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not okay - stop that now");
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[capacity];
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("not the juice");
        }

        size++;
        if (size > capacity) {
            capacity = capacity * 2;
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < size - 1; i++) {
                copy[i] = s[i];
            }
            copy[size - 1] = item;
            s = copy;
        }
        else {
            s[size - 1] = item;
        }
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniform(size);
        Item temp = s[index];
        s[index] = s[size - 1];
        s[size - 1] = null;
        size--;
        if (size < capacity / 4) {
            capacity = capacity / 2;
            Item[] copy = (Item[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                copy[i] = s[i];
            }
            s = copy;
        }
        return temp;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int index = StdRandom.uniform(size);
        return s[index];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RndIterator<>();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // make an instance of class randomised queue
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        s.enqueue("ben");
        s.enqueue("bill");
        s.enqueue("fffffff");
        s.enqueue("john");
        System.out.println(s.sample());
        System.out.println(s.size());
        for (String p : s) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println(s.dequeue());
        for (String p : s) {
            System.out.println(p);
        }

    }

}
