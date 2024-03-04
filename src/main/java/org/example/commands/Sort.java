package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.HumanComparator;
/**
 * Class for the "sort" command
 */
public class Sort extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Sort(CommandLine cl, CollectionManager cm) {
        super("sort", "Sorts collection by default (in this case by DAMAGE stat values).");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "sort" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
            if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
            else {
                cm.getCollection().sort(new HumanComparator());
            }
            return new Feedbacker(">Sorted successfully.");
    }
}
