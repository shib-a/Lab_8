package org.example.commands;

public interface Commandable {
    void execute();
    String getDescription();
    String getName();
    boolean checkIsValidArg();
}
