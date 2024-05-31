package client.commands;
import common.AbstractCommand;

import common.Feedbacker;
import common.UserData;

/**
 * Class for the "save" command
 */
public class Save extends AbstractCommand{
    public Save() {
        super("save", "Saves the collection by writing it to a file.");
    }
    /**
     * Executes the "save" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
//        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. see 'help' for reference.");
//        cm.saveToFile();
//        return new Feedbacker(">Collection saved.");
        return new Feedbacker("");
    }

}