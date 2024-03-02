package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

public class Info extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Info(CommandLine cl, CollectionManager cm) {
        super("info", "Shows meta information about the collection");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
            if(!arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
            cl.printLn("Collection size: "+cm.getCollection().size());
            cl.printLn("Collection initialization date: "+cm.getInitDate());
            return new Feedbacker("Shown successfully");
    }
}
