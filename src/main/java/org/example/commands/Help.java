package org.example.commands;

import org.example.collection.HumanCollection;

public class Help extends AbstractCommand {

    public Help(){super(false,false);}

    @Override
    public void execute() {
        System.out.println("These are the possible commands:" +
                "Dick.suck" +
                "balls.drop");
    }

    @Override
    public void execWithCol(HumanCollection obj) {

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "lists all commands and their descriptions";
    }

    @Override
    public boolean checkIsValidArg() {
        return false;
    }
}
