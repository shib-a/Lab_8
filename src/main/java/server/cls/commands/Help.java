package server.cls.commands;

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
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        argm = arg[1];
        if(!arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        for(AbstractCommand el: com.getCommandList().values()){
            System.out.println(el.getName() + "-" +el.getDesc());
        }
        return new Feedbacker(">Shown successfully.");
    }
}
