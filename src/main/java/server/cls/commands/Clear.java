package server.cls.commands;

import common.AbstractCommand;
import common.Access;
import common.Feedbacker;
import common.User;
import server.CommandLine;
import server.managers.CollectionManager;

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
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.", user);
        if(!user.getAccess().equals(Access.FULL_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.", user);
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.", user);
        cm.getCollection().clear();
        return new Feedbacker(">Collection cleared.", user);
    }
}
