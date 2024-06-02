package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;
import server.*;

/**
 * Class for the "save" command
 */
public class Save extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Save(CommandLine cl, CollectionManager cm) {
        super("save", "Saves the collection by writing it to a file.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "save" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. see 'help' for reference.");
        cm.saveToFile();
        return new Feedbacker(">Collection saved.");
    }
}