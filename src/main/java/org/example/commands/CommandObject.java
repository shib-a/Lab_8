package org.example.commands;

import java.io.Serializable;

public class CommandObject implements Serializable {
    private static final long serialVersionUID = 1L;
    String[] argument;
    AbstractCommand command;
    public CommandObject(AbstractCommand command,String[] arg){
        this.argument = arg;
        this.command = command;
    }
    public AbstractCommand getInputCommand(){return command;}
    public String[] getArgument(){return argument;}
    public void deriveCommand(){

    }

    @Override
    public String toString() {
        return "CommandObject{" +
                "argument='" + argument + '\'' +
                ", command=" + command +
                '}';
    }
}
