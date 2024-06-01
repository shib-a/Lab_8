package client.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

/**
 * Class for "" command
 */
public class Clear extends AbstractCommand {
    public Clear() {
        super("clear", "Clear the collection.");
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
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
//        cm.getCollection().clear();
//        return new Feedbacker(">Collection cleared.");
        return new Feedbacker("");
    }
}
