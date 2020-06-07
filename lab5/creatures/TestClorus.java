package creatures;

import huglife.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.awt.*;
import java.util.HashMap;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus clorus = new Clorus(3.2);
        Assert.assertEquals(3.2, clorus.energy(), 0.01);
        Assert.assertEquals(new Color(34, 0 ,231), clorus.color());
        clorus.move();
        Assert.assertEquals(3.17, clorus.energy(), 0.01);
        clorus.stay();
        Assert.assertEquals(3.16, clorus.energy(), 0.01);
        clorus.move();
        Assert.assertEquals(3.13, clorus.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2.4);
        Clorus baby = c.replicate();
        Assert.assertEquals(1.2, baby.energy(), 0.01);
        Assert.assertEquals(1.2, c.energy(), 0.01);
    }

    @Test
    public void testChooseAction() {
        Clorus c = new Clorus(2.4);
        HashMap<Direction, Occupant> noEmpty = new HashMap<>();
        noEmpty.put(Direction.TOP, new Impassible());
        noEmpty.put(Direction.BOTTOM, new Impassible());
        noEmpty.put(Direction.RIGHT, new Impassible());
        noEmpty.put(Direction.LEFT, new Plip());
        Action expected = new Action(Action.ActionType.STAY);
        Action actual = c.chooseAction(noEmpty);
        Assert.assertEquals(expected, actual);

        HashMap<Direction, Occupant> leftPlips = new HashMap<>();
        leftPlips.put(Direction.TOP, new Empty());
        leftPlips.put(Direction.BOTTOM, new Empty());
        leftPlips.put(Direction.RIGHT, new Empty());
        leftPlips.put(Direction.LEFT, new Plip());
        expected = new Action(Action.ActionType.ATTACK, Direction.LEFT);
        actual = c.chooseAction(leftPlips);
        Assert.assertEquals(expected, actual);

        Clorus c2 = new Clorus(0.6);

        HashMap<Direction, Occupant> topEmpty = new HashMap<>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());

        //energy >= 1, replicate
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        actual = c.chooseAction(topEmpty);
        Assert.assertEquals(expected, actual);

        //energy < 1, move
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        actual = c2.chooseAction(topEmpty);
        Assert.assertEquals(expected, actual);



    }

}
