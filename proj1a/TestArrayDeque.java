import static org.junit.Assert.*;

public class TestArrayDeque{

    public static void testRemoveLast(ArrayDeque<Integer> a){
        a.addFirst(0);
        int actual = a.removeLast();
        int expected = 0;
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque();
        testRemoveLast(a);
    }
}