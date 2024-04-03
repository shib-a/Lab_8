package server.cls.commands;

import java.io.Serializable;

public class CommandObject implements Serializable {
    private static final long serialVersionUID = 1L;
    String argument;
    AbstractCommand command;
    public CommandObject(AbstractCommand command, String arg){
        this.argument = arg;
        this.command = command;
    }
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
