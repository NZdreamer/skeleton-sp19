package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    /** color */
    private int r;
    private int g;
    private int b;

    /** energy change */
    private double moveEnergyLose = 0.03;
    private double stayEnergyLose = 0.01;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** return the color of clorus, always return 34, 0, 231 */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /** return energy */
    public double energy() {
        return energy;
    }

    /** lose 0.03 energy when move */
    public void move() {
        energy = energy - moveEnergyLose;
    }

    /** lose 0.01 units of energy when stay */
    public void stay() {
        energy = energy - stayEnergyLose;
    }

    /** gain the other creature's energy when attack */
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    /** when replicate, keep 50% energy and give 50% energy to its offspring */
    public Clorus replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    /** chooseAction following rules below
     * 1. If there are no empty squares, the Clorus will STAY
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque();
        Deque<Direction> plips = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            }
            if (neighbors.get(d).name().equals("plip")) {
                plips.add(d);
            }
        }
        //rule1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        //rule2
        else if (!plips.isEmpty()) {
            Direction ranDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, ranDir);
        }
        //rule3
        else if (energy >= 1) {
            Direction ranDir = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, ranDir);
        }
        //rule4
        else {
            Direction ranDir = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, ranDir);
        }

    }

}
