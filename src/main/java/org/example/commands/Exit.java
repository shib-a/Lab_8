package org.example.commands;

import org.example.collection.HumanCollection;

public class Exit extends AbstractCommand{
    public Exit() {
        super(false,false);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execWithCol(HumanCollection obj) {
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean checkIsValidArg() {
        return false;
    }
}
