import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int MIN_CAPACITY = 2;
    private int size = 0;
    private Item[] values;

    // construct an empty randomized queue
    public RandomizedQueue() {
        values = (Item[]) new Object[MIN_CAPACITY];
    }

    // unit testing (required)
    public static void main(String[] args) {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        assertNotNull(item);
        if (size == values.length) {
            resize(size * 2);
        }
        values[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        assertNotEmpty();
        int index = randomIndex();
        Item item = values[index];
        values[index] = values[size - 1];
        values[size - 1] = null;
        size--;
        autoShrink();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        assertNotEmpty();
        return values[randomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void assertNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private void assertNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    private void autoShrink() {
        if (size > 0 && size == values.length / 4) resize(values.length / 2);
    }

    private void resize(int capacity) {
        if (capacity >= size) {
            Item[] temp = (Item[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = values[i];
            }
            values = temp;
        }
    }

    private int randomIndex() {
        return StdRandom.uniform(0, size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] temp;
        private int index;

        public RandomizedQueueIterator() {
            temp = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                temp[i] = values[i];
            }
            StdRandom.shuffle(temp);

        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return temp[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
