package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;
import server.CommandLine;
import server.CollectionManager;

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
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
        cm.getCollection().clear();
        return new Feedbacker(">Collection cleared.");
    }
}
