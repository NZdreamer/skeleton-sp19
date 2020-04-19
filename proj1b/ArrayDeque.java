
public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int arraySize = 8;


    public ArrayDeque() {
        items = (T[]) new Object[arraySize];
        size = 0;
        nextFirst = arraySize - 1;
        nextLast = size;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.arraySize];
        for (int i = 0; i < other.size(); i++) {
            items[i] = (T) other.items[i];
        }
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    private int next(int next) {
        if (next < 0) {
            next = arraySize + next;
        } else if (next > arraySize - 1) {
            next = next - arraySize;
        }
        return next;
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIndex = next(nextFirst + 1);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            a[newIndex] = items[oldIndex];
            oldIndex = next(oldIndex + 1);
        }
        arraySize = capacity;
        nextFirst = capacity - 1;
        nextLast = size;
//        if (nextFirst < nextLast) {
//            System.arraycopy(items, nextFirst, a, 0, size + 1);
//            nextFirst = 0;
//            nextLast = size + 2;
//        } else {
//            System.arraycopy(items, 0, a, 0, nextLast);
//            System.arraycopy(items, nextFirst, a, capacity - size + nextFirst - 1,
//                    size - nextFirst + 1);
//            nextFirst = next(capacity - size + nextFirst - 1);
//        }

        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(arraySize * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = next(nextFirst - 1);
    }

    @Override
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(arraySize * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = next(nextLast + 1);
    }

    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[next(nextFirst + 1 + i)] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size > 16 && arraySize / size > 3) {
            resize(arraySize / 2);
        }
        nextFirst = next(nextFirst + 1);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size = size - 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size > 16 && arraySize / size > 3) {
            resize(arraySize / 2);
        }
        nextLast = next(nextLast - 1);
        T item = items[nextLast];
        items[nextLast] = null;
        size = size - 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[next(nextFirst + 1 + index)];
    }

}
