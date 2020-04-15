import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest{
    @Test
    public void testFlik() {
        boolean actual1 = Flik.isSameNumber(1,2);
        boolean exp1 = false;
        assertEquals(exp1, actual1);

        assertTrue(Flik.isSameNumber(128, 128));
    }

}