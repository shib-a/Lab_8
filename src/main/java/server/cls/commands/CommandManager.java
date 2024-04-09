package server.cls.commands;

import common.AbstractCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for handling the commands and keeping track of command history
 */
public class CommandManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, AbstractCommand> commandList;
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
//        try {
//            var l = new BufferedOutputStream(new FileOutputStream("log.txt",true));
//            l.write(name.getBytes());
//            l.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
