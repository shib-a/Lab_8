package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
/**
 * Class for "" command
 */
public class Clear extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Clear(CommandLine cl, CollectionManager cm) {
        super("clear", "Clear the collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "clear" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        cm.getCollection().clear();
        return new Feedbacker(">Collection cleared.");
    }
}
