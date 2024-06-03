package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;
import server.managers.CommandManager;

import java.io.Serializable;

/**
 * Class for the "help" command
 */
public class Help extends AbstractCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient CommandManager com;
    String argm;
    public Help(CommandManager com) {
        super("help", "Shows information about available commands.");
        this.com=com;
    }
    /**
     * Executes the "help" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.", user);
        StringBuilder str = new StringBuilder();
        for(AbstractCommand el: com.getCommandList().values()){
            str.append(el.getName()).append("\t").append(el.getDesc()).append("\n");
        }
        return new Feedbacker(str.append(">Shown successfully.").toString(), user);
    }
}
