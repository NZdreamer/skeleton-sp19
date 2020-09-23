import org.w3c.dom.Node;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    public K key;
    public V value;

    private int size = 0;

    private Node bst;


//    public BSTMap() {
//    }


    private class Node {
        public Node left;
        public Node right;
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /* a private helper method for clear()
    clear all that in the binary search tree recursively
    */
//    private void clear(Node n) {
//        if (n == null) {
//            return;
//        }
//        else {
//            if (n.left != null) {
//                clear(n.left);
//                n.key = null;
//                n.value = null;
//                size -= 1;
//            }
//            if (n.right != null) {
//                clear(n.right);
//                n.key = null;
//                n.value = null;
//                size -= 1;
//            }
//        }
//    }

    @Override
    public void clear() {
        bst = null;
        size = 0;
        //clear(bst);
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    /* a private recursive helper method for public get method,
    get the value of the given key
     */
    private Node get(K key, Node n) {
        if (n == null) {
            return null;
        }
        else {
            int cmp = n.key.compareTo(key);
            if (cmp < 0) {
                n = get(key, n.right);
            }
            if (cmp > 0) {
                n = get(key, n.left);
            }
            return n;
        }
    }

    @Override
    public V get(K key) {
        Node nGet = get(key, bst);
        if (nGet == null) {
            return null;
        }
        else {
            return nGet.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    /* a private recursive helper method for public method put,
    put the key and value into node n */
    private Node put(K key, V value, Node n) {
        if (n == null) {
            size += 1;
            n = new Node(key, value);
            return n;
        }
        else {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) {
                n.left = put(key, value, n.left);
            }
            else if (cmp > 0) {
                n.right = put(key, value, n.right);
            }
            return n;
        }
    }

    /* put key and value into this binary search tree */
    @Override
    public void put(K key, V value) {
        bst = put(key, value, bst);
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /* an iterator that iterates over the keys of the dictionary */
    private class BSTMapIterator implements Iterator<K> {
        public BSTMapIterator() {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public K next() {
            return null;
        }
    }
}
