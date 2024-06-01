package client.commands;

import client.CommandLine;
import common.AbstractCommand;
/**
 * Class for "add" command
 */
public class Add extends AbstractCommand{
    private transient CommandLine cl;
    public Add(CommandLine cl) {
        super("add", "Add a new element to collection.",true);
        this.cl=cl;
    }

    /**
     * Executes the "add" command
     * @param arg
     * @return Feedbacker
     */
//    @Override
//    public Feedbacker execute(String arg) {
//        try {
//            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
//            cl.printLn(">Creating new Human:");
//            Ask.askHuman(cl);
//            return new Feedbacker("");
//        } catch (Ask.AskBreaker e) {
//            return new Feedbacker(false,">Exited process.");
//        }
//    }
}
