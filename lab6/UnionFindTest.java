import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {
    UnionFind u = new UnionFind(10);

    @Test
    public void testBasic() {

        u.union(0,1);
        assertEquals(1,u.parent(0));
        u.union(1,5);
        assertEquals(true,u.connected(5,0));
        assertEquals( 1,u.parent(5));
        assertEquals(false,u.connected(1,6));
        u.union(9,8);
        u.union(5,8);
        assertEquals(1,u.parent(8));
        assertEquals(5,u.sizeOf(8));
    }
}
