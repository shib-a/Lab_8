package org.example.commands;

public class Help implements Commandable{

    public Help(){}

    @Override
    public void execute() {

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
