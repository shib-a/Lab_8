package org.example.commands;

import org.example.collection.HumanCollection;

public class Show extends AbstractCommand{
    public Show() {
        super(false,true);
    }
    @Override
    public void execute() {

    }

    @Override
    public void execWithCol(HumanCollection obj) {
        if (!obj.getHumanArrayList().isEmpty()) {
            System.out.println(obj.getHumanArrayList().toString());
        } else {
            System.out.println("Collection is empty. At least, for now :)");
        }
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
