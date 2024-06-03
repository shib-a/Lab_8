package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import client.CommandLine;
import common.User;
import server.managers.CommandManager;

/**
 * Executes the "get_history" command
 */
public class GetHistory extends AbstractCommand {
    private CommandLine cl;
    private CommandManager com;
    public GetHistory(CommandLine cl, CommandManager com) {
        super("get_history", "Shows entered commands.");
        this.cl = cl;
        this.com=com;
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
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.", user);
        cl.printLn(com.getCommandHistory());
        return new Feedbacker(">Showed successfully.", user);
    }
}
