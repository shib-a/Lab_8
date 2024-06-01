package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

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
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        cl.printLn(com.getCommandHistory());
//        return new Feedbacker(">Showed successfully.");
        return new Feedbacker("");
    }
}
