public class LinkedListDeque<T> {

    private IntNode sentinel;
    private int size;
    private T s;

    private class IntNode {
        public T item;
        public IntNode next;
        public IntNode previous;

        public IntNode(IntNode p, T i, IntNode n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, s, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new IntNode(null, s , null);
        IntNode p = sentinel.next;
        IntNode o = other.sentinel.next;
        for (int i = 0; i < other.size(); i++) {
            p = o;
            p = p.next;
            o = o.next;
        }
    }

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.previous = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.previous = new IntNode(sentinel.previous, item, sentinel);
        sentinel.previous.previous.next = sentinel.previous;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.previous.item;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size = size - 1;
        return last;
    }

    public T get(int index) {
        if (index < size) {
            IntNode p = sentinel.next;
            while (index != 0) {
                p = p.next;
                index = index - 1;
            }
            return p.item;
        }
        return null;
    }

    /** helper methods: to implement a recursive method in a class
     * that is not itself recursive */
    private T getRecursive(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index < size) {
            return getRecursive(index, sentinel.next);
        }
        return null;
    }

}
