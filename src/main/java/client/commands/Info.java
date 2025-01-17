package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Class for the "info" command
 */
public class Info extends AbstractCommand {
    public Info() {
        super("info", "Shows meta information about the collection.");
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
//            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//            cl.printLn("Collection size: "+cm.getCollection().size());
//            cl.printLn("Collection initialization date: "+cm.getInitDate());
//            return new Feedbacker(">Shown successfully");
        return new Feedbacker("", user);
    }
}
