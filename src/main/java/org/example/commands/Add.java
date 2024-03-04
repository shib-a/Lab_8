package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

/**
 * Class for "add" command
 */
public class Add extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Add(CommandLine cl, CollectionManager cm) {
        super("add", "Add a new element to collection.");
        this.cl = cl;
        this.cm=cm;
    }

    /**
     * Executes the "add" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        try {
            if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
            cl.printLn(">Creating new Human:");
            Human h = Ask.askHuman(cl,cm.getUnusedId());
            if(h!=null && h.validate()){
                cm.add(h);
                return new Feedbacker(">Added successfully.");
            } else return new Feedbacker(false,">Failed to add. Invalid arguments.");
        } catch (Ask.AskBreaker e) {
            return new Feedbacker(false,">Exited process.");
        }
    }
}
