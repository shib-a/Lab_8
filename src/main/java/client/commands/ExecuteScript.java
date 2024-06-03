package client.commands;
import common.Feedbacker;
import common.AbstractCommand;
import common.User;

/**
 * Class for the "execute_script" command
 */
public class ExecuteScript extends AbstractCommand{
    public ExecuteScript() {
        super("execute_script (file_name)", "Executes commands from the entered file.");
    }
    /**
     * Executes the "execute_script" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(arg.isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference.", user);
        return new Feedbacker(">Executing script...", user);
    }
}
