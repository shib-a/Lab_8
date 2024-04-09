package client.commands;
import common.AbstractCommand;

import common.Feedbacker;
/**
 * Class for the "save" command
 */
public class Save extends AbstractCommand{
    public Save() {
        super("save", "Saves the collection by writing it to a file.");
    }
    /**
     * Executes the "save" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg) {
//        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. see 'help' for reference.");
//        cm.saveToFile();
//        return new Feedbacker(">Collection saved.");
        return new Feedbacker("");
    }

}