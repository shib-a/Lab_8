package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import server.*;

/**
 * Executes the "show" command
 */
public class Show extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Show(CommandLine cl, CollectionManager cm) {
        super("show", "Shows the elements of collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "show" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg) {
        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.");
        if (cm.getCollection().isEmpty()) return new Feedbacker(">Collection is empty.");
        StringBuilder str = new StringBuilder();
        for(Human el:cm.getCollection()){
            str.append(el.toString()).append("\n");
        }
        return new Feedbacker(str.append(">Elements shown.").toString());
    }
}
