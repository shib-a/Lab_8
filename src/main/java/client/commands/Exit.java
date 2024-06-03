package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Class for the "exit" command
 */
public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "Stops the program without saving the collection.");
    }
    /**
     * Executes the "exit" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        return new Feedbacker("",user);
    }
}
