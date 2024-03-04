package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
/**
 * Class for the "execute_script" command
 */
public class ExecuteScript extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public ExecuteScript(CommandLine cl, CollectionManager cm) {
        super("execute_script (file_name)", "Executes commands from the entered file.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "execute_script" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference.");
        return new Feedbacker(">Executing script...");
    }
}
