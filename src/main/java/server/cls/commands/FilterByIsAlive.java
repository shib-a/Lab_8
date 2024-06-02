package server.cls.commands;

import common.AbstractCommand;
import common.Feedbacker;
import common.User;
import server.*;

/**
 * Class for the "filter_by_is_alive" command
 */
public class FilterByIsAlive extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public FilterByIsAlive(CommandLine cl, CollectionManager cm) {
        super("filter_by_is_alive (true/false)", "Shows elements with entered isAlive value.");
        this.cl = cl;
        this.cm=cm;
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
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        try{
            StringBuilder str = new StringBuilder();
            var val = Boolean.parseBoolean(arg.trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker(">Empty collection.");} else{
            str.append(cm.getCollection().stream().filter(el -> el.getIsAlive()==val).map(el -> el.toString()).reduce((a,b) -> a+"\n"+b).get());
            return new Feedbacker(str.append(">Elements shown successfully.").toString());}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
