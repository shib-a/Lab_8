package server.cls.commands;

import common.*;
import server.CommandLine;
import server.managers.CollectionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Class for "" command
 */
public class Clear extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Clear(CommandLine cl, CollectionManager cm) {
        super("clear", "Clear the collection.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "clear" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.", user);
        if(user.getAccess().equals(Access.RESTRICTED_ACCESS)) return new Feedbacker(false, ">You don't have permission for this.", user);
        if(!arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. see 'help' for reference.", user);
        Iterator<Human> iterator = cm.getCollection().iterator();
        while (iterator.hasNext()){
            var temp = iterator.next();
            if (temp.getOwner().equals(user.getName())){iterator.remove();}
        }
//        cm.getCollection().stream().filter(el -> !el.getOwner().equals(user.getName())).collect(Collectors.toCollection(ArrayList::new));
//        cm.getCollection().clear();
        return new Feedbacker("Cleared", user);
    }
}
