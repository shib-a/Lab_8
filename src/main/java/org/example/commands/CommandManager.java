package org.example.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for handling the commands and keeping track of command history
 */
public class CommandManager {
    private Map<String,AbstractCommand> commandList;
    public ArrayList<String> commandHistory;
    public CommandManager(){this.commandList = new HashMap<>();this.commandHistory= new ArrayList<String>();}
    public Map<String, AbstractCommand> getCommandList() {
        return commandList;
    }
    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
    public void addToHistory(String name){
        commandHistory.add(name);
    }
}
