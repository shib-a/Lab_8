package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;

public class Exit extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Exit(CommandLine cl, CollectionManager cm) {
        super("exit", "Stops the program without saving");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        return new Feedbacker("exit");
    }
}
