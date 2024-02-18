package org.example.commands;

import org.example.collection.HumanCollection;

public class Add extends AbstractCommand{
    public Add() {
        super(true, true);
    }

    @Override
    public void execute() {

    }

    @Override
    public void execWithCol(HumanCollection obj) {

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
