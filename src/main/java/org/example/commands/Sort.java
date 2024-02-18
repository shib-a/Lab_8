package org.example.commands;

import org.example.classes.HumanComparator;
import org.example.collection.HumanCollection;

import java.util.Comparator;

public class Sort extends AbstractCommand{

    public Sort() {
        super(false,true);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execWithCol(HumanCollection obj) {
        HumanComparator hcomp = new HumanComparator();
        obj.getHumanArrayList().sort(hcomp);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean checkIsValidArg() {
        return false;
    }
}
