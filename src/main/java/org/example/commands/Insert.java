package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
import org.example.classes.Stat;

public class Insert extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Insert(CommandLine cl, CollectionManager cm) {
        super("insert (positive_integer_value)", "Inserts a new element into entered position");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        try {
            if (arg[1].isEmpty()) return new Feedbacker(false, "Wrong argument usage. see 'help' for reference");
            var val = Integer.parseInt(arg[1].trim());
            if(val<=cm.getCollection().size()){
                cl.printLn("Creating new Human for insertion:");
                Human h = Ask.askHuman(cl, cm.getUnusedId());
                if (h != null && h.validate()) {
                    cm.insert(val,h);
                    return new Feedbacker("Inserted successfully");
                } else return new Feedbacker(false, "Failed to insert. Invalid arguments.");
            } else return new Feedbacker(false,"Failed. no such index");
        } catch (NumberFormatException|Ask.AskBreaker e) {
            return new Feedbacker(false, "Failure");
        }
    }
}
