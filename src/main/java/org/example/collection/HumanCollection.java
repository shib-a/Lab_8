package org.example.collection;

import org.example.classes.Human;

import java.util.ArrayList;
import java.util.Arrays;

public class HumanCollection{
    public ArrayList<Human> humanArrayList = new ArrayList<>();
    public HumanCollection (Human... insts){
        humanArrayList.addAll(Arrays.asList(insts));
    }

    @Override
    public String toString() {
        return "" + humanArrayList.toString();
    }
}
