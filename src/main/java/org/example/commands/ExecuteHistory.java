package org.example.commands;

import org.example.CommandLine;
/**
 * Executes the "clear" command
 */
public class ExecuteHistory extends AbstractCommand{
    private CommandLine cl;
    private CommandManager com;
    public ExecuteHistory(CommandLine cl, CommandManager com) {
        super("get_history", "Shows entered commands");
        this.cl = cl;
        this.com=com;
    }
    /**
     * Executes the "clear" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        cl.printLn(com.getCommandHistory());
        return new Feedbacker("Showed successfully");
    }
}
