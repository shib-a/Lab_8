package org.example.classes;

import java.util.Comparator;

public class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return  (int)o2.getStat(Stat.DAMAGE) - (int) o1.getStat(Stat.DAMAGE);
    }
}
