package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
/**
 * Class for the "help" command
 */
public class Help extends AbstractCommand{
    private CommandLine cl;
    private CommandManager com;
    public Help(CommandLine cl, CommandManager com) {
        super("help", "Shows information about available commands.");
        this.cl = cl;
        this.com=com;
    }
    /**
     * Executes the "help" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        for(AbstractCommand el: com.getCommandList().values()){
            cl.printF(el.getName(),el.getDesc());
        }
        return new Feedbacker(">Shown successfully.");
    }
}
