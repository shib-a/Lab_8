package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;
import server.CommandLine;
import server.managers.CollectionManager;

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
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.", user);
            return new Feedbacker(new StringBuilder().append("Collection size: ").append(cm.getCollection().size()).append("Collection initialization date: ").append(cm.getInitDate()).append(">Shown successfully").toString(), user);
    }
}
