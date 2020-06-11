package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    @Override
    public Iterator<T> iterator();

    /** return the size of the buffer */
    public int capacity();

    /** return numbers of items currently in the buffer */
    public int fillCount();

    /** add item x to the end */
    public void enqueue(T x);

    /** delete and return item form the front */
    public T dequeue();

    /**return (but do not delete) item from the front */
    public T peek();

    /** is the buffer empty (fullCount = 0)? */
    public default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /** id the buffer full (fullCount = capacity)? */
    public default boolean isFull() {
        if (fillCount() >= capacity()) {
            return true;
        }
        return false;
    }


}
