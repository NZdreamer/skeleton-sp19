import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test() {
        while (true) {
            StudentArrayDeque<Integer> sad1 = new StudentArrayDeque();
            ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution();
            String message = "";
            for (int i = 0; i < 15; i++) {
                Integer[] ran = new Integer[15];
                ran[i] = StdRandom.uniform(4);
                if (ran[i] == 0) {
                    Integer num = StdRandom.uniform(10);
                    sad1.addLast(num);
                    ads1.addLast(num);
                    message = message + "addLast(" + num + ") \n";
                }
                else if (ran[i] == 1) {
                    Integer num = StdRandom.uniform(10);
                    sad1.addFirst(num);
                    ads1.addFirst(num);
                    message = message + "addFirst(" + num + ") \n";
                }
                else if (ran[i] == 2) {
                    if (sad1.size() == 0) {
                        continue;
                    }
                    message = message + "removeFirst() \n";
                    Assert.assertEquals(message, sad1.removeFirst(), ads1.removeFirst());
                }
                else {
                    if (sad1.size() == 0) {
                        continue;
                    }
                    message = message + "removeLast() \n";
                    Assert.assertEquals(message, sad1.removeLast(), ads1.removeLast());
                }
            }
        }
    }

}