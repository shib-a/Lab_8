package server.cls.commands;

import org.example.CommandLine;

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
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        cl.printLn(com.getCommandHistory());
        return new Feedbacker(">Showed successfully.");
    }
}
