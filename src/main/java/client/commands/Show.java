package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

/**
 * Executes the "show" command
 */
public class Show extends AbstractCommand{ ;
    public Show() {
        super("show", "Shows the elements of collection.");
    }
    /**
     * Executes the "show" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
//        if (!arg.isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.");
//        if (cm.getCollection().isEmpty()) return new Feedbacker(">Collection is empty.");
//        else{for(Human el:cm.getCollection()){
//            cl.printLn(el.toString());
//        }
//        return new Feedbacker(">Elements shown.");}
        return new Feedbacker("");
    }
}
