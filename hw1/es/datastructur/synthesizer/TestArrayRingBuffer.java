package es.datastructur.synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void test() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        Assert.assertEquals(true, arb.isEmpty());
        arb.enqueue(3);
        Assert.assertEquals(3,arb.dequeue());
        Assert.assertEquals(true, arb.isEmpty());
        arb.enqueue(4);
        arb.enqueue(5);
        Assert.assertEquals(4,arb.peek());
        Assert.assertEquals(false, arb.isEmpty());
        arb.enqueue(6);
        arb.enqueue(7);
        Assert.assertEquals(4,arb.peek());
        Assert.assertEquals(4,arb.dequeue());
    }
}
