package server.cls.commands;

import common.AbstractCommand;

import java.io.Serializable;

public class CommandSerializer implements Serializable {
    public CommandSerializer(){}
    public String serialize(AbstractCommand command){
        return command.toString();
    }
}
