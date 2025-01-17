package server;

import java.util.Comparator;

import common.Human;
import common.Stat;

/**
 * This class makes methods like sort() work with Human instances based on their DAMAGE stat value.
 */
public class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return  (int)o2.getStat(Stat.DAMAGE) - (int) o1.getStat(Stat.DAMAGE);
    }
}
