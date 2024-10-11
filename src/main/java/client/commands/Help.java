package client.commands;

import common.AbstractCommand;
import java.io.Serializable;
import common.Feedbacker;
import common.User;

/**
 * Class for the "help" command
 */
public class Help extends AbstractCommand implements Serializable {
    public Help() {
        super("help", "Shows information about available commands.");
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
//        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        for(AbstractCommand el: com.getCommandList().values()){
//            System.out.println(el.getName() + "-" +el.getDesc());
//        }
//        return new Feedbacker(">Shown successfully.");
        return new Feedbacker("", user);
    }
}
