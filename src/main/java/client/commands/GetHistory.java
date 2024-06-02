package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Executes the "get_history" command
 */
public class GetHistory extends AbstractCommand{
    public GetHistory() {
        super("get_history", "Shows entered commands.");
    }
    /**
     * Executes the "get_history" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        cl.printLn(com.getCommandHistory());
//        return new Feedbacker(">Showed successfully.");
        return new Feedbacker("");
    }
}
