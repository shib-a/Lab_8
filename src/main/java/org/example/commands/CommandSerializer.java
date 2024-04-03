package org.example.commands;

import java.io.Serializable;

public class CommandSerializer implements Serializable {
    public CommandSerializer(){}
    public String serialize(AbstractCommand command){
        return command.toString();
    }
}
