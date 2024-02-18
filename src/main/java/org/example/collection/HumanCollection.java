package org.example.collection;

import org.example.classes.Human;

import java.util.ArrayList;
import java.util.Arrays;

public class HumanCollection{
    private ArrayList<Human> humanArrayList = new ArrayList<>();
    public HumanCollection (Human... insts){
        getHumanArrayList().addAll(Arrays.asList(insts));
    }

    @Override
    public String toString() {
        return "" + getHumanArrayList().toString();
    }

    public ArrayList<Human> getHumanArrayList() {
        return humanArrayList;
    }

    public void setHumanArrayList(ArrayList<Human> humanArrayList) {
        this.humanArrayList = humanArrayList;
    }
}
