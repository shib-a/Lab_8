package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
/**
 * Class for the "exit" command
 */
public class Exit extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Exit(CommandLine cl, CollectionManager cm) {
        super("exit", "Stops the program without saving the collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "exit" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        return new Feedbacker("exit");
    }
}
