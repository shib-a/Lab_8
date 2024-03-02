package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;

public class GetHistory extends AbstractCommand{
    private CommandLine cl;
    private CommandManager com;
    public GetHistory(CommandLine cl, CommandManager com) {
        super("get_history", "Shows entered commands");
        this.cl = cl;
        this.com=com;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        cl.printLn(com.getCommandHistory());
        return new Feedbacker("Showed successfully");
    }
}
