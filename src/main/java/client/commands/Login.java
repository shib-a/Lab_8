package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

/**
 * Class for the "info" command
 */
public class Login extends AbstractCommand {
    public Login() {
        super("login", "authorize");
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
//            if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//            cl.printLn("Collection size: "+cm.getCollection().size());
//            cl.printLn("Collection initialization date: "+cm.getInitDate());
//            return new Feedbacker(">Shown successfully");
        return new Feedbacker("");
    }
}
