package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import server.CommandLine;
import server.Ask;
import server.CollectionManager;
import server.Human;

/**
 * Class for "add" command
 */
public class Add extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    private RuntimeEnv re;
    public Add(CommandLine cl, CollectionManager cm, RuntimeEnv re) {
        super("add", "Add a new element to collection.");
        this.cl = cl;
        this.cm=cm;
        this.re=re;
    }

    /**
     * Executes the "add" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg) {
        try {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.");
            cl.printLn(">Creating new Human:");
            Human h = Ask.askHuman(re.getCurrHumanData(),cm.getUnusedId());
            if(h!=null && h.validate()){
                cm.add(h);
                return new Feedbacker(">Added successfully.");
            } else return new Feedbacker(false,">Failed to add. Invalid arguments.");
        } catch (Ask.AskBreaker e) {
            return new Feedbacker(false,">Exited process.");
        }
    }
}
