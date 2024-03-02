package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
import org.example.classes.HumanComparator;
import org.example.collection.HumanCollection;

import java.util.Comparator;

public class Sort extends AbstractCommand{

    private CommandLine cl;
    private CollectionManager cm;
    public Sort(CommandLine cl, CollectionManager cm) {
        super("sort", "Sorts collection by default (in this case by damage value)");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
            if(!arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
            else {
                cm.getCollection().sort(new HumanComparator());
            }
            return new Feedbacker("Sorted successfully");
    }
}
