package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;
import server.CommandLine;
import server.CollectionManager;

/**
 * Class for the "info" command
 */
public class Info extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Info(CommandLine cl, CollectionManager cm) {
        super("info", "Shows meta information about the collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "info" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
            return new Feedbacker(new StringBuilder().append("Collection size: ").append(cm.getCollection().size()).append("Collection initialization date: ").append(cm.getInitDate()).append(">Shown successfully").toString());
    }
}
