package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.User;

/**
 * Class for the "filter_by_is_alive" command
 */
public class FilterByIsAlive extends AbstractCommand{
    public FilterByIsAlive() {
        super("filter_by_is_alive (true/false)", "Shows elements with entered isAlive value.");
    }
    /**
     * Executes the "filter_by_is_alive" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
//        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        try{
//            var val = Boolean.parseBoolean(arg.trim());
//            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
//            for(Human el: cm.getCollection()){
//                if (el.isAlive==val) cl.printLn(el);
//            }
//            return new Feedbacker(">Elements shown successfully.");}
//        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.");}
        return new Feedbacker("", user);
    }
}
