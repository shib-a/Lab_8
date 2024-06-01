package server.cls.commands;

import common.AbstractCommand;

import java.io.Serializable;

public class SerializedCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    private AbstractCommand command;
    public SerializedCommand(AbstractCommand command){
        this.command = command;
    }
    public String getDesc() {return command.getDesc();}

    @Override
    public String toString() {
        return "SerializedCommand{" +
                command.toString() +
                '}';
    }
}
