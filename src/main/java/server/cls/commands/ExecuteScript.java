package server.cls.commands;

import common.AbstractCommand;
import common.Access;
import common.Feedbacker;
import common.User;
import server.*;
import server.managers.CollectionManager;

/**
 * Class for the "execute_script" command
 */
public class ExecuteScript extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public ExecuteScript(CommandLine cl, CollectionManager cm) {
        super("execute_script (file_name)", "Executes commands from the entered file.");
        this.cl = cl;
        this.cm=cm;
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
        if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.", user);
        if(user.getAccess().equals(Access.FULL_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.", user);
        if(arg.isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference.", user);
        return new Feedbacker(">Executing script...", user);
    }
}
