import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(1);
        }
        int entryCount = 0;
        for (Integer i : deque
        ) {
            entryCount++;
        }
        System.out.println(entryCount);
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        assertNotNull(item);
        final Node<Item> f = first;
        final Node<Item> newNode = new Node<>(null, item, f);
        first = newNode;
        if (f == null) {
            first = newNode;
            if (last == null)// no first node and last node
                last = first;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    private void assertNotNull(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // add the item to the back
    public void addLast(Item item) {
        assertNotNull(item);
        final Node<Item> f = last;
        final Node<Item> newNode = new Node<>(f, item, null);
        last = newNode;
        if (f == null) {
            last = newNode;
            if (first == null) {// no first node and last node
                first = last;
            }
        } else {
            f.next = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        final Node<Item> nodeFirst = first;
        if (nodeFirst == null)
            throw new NoSuchElementException();
        return unlinkFirst(nodeFirst);
    }

    /**
     * Unlinks non-null first node f.
     */
    private Item unlinkFirst(Node<Item> node) {
        final Item element = node.item;
        final Node<Item> next = node.next;
        node.item = null;
        node.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    /**
     * Unlinks non-null last node last.
     */
    private Item unlinkLast(Node<Item> node) {
        final Item element = node.item;
        final Node<Item> prev = node.prev;
        node.item = null;
        node.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    // remove and return the item from the back
    public Item removeLast() {
        final Node<Item> nodeLast = last;
        if (nodeLast == null)
            throw new NoSuchElementException();
        return unlinkLast(nodeLast);
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DqueIterator();
    }

    private class DqueIterator implements Iterator<Item> {
        private Node<Item> current = first;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Item next() {
            Item item = current.item;
            if (hasNext()) {
                current = current.next;
            }
            nextIndex++;
            return item;
        }
    }

    private class Node<Item> {
        private Node<Item> prev;
        private Node<Item> next;
        private Item item;

        public Node(Node<Item> prev, Item item, Node<Item> next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

}
