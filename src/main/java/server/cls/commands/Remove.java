package server.cls.commands;

import common.*;
import server.CommandLine;
import server.managers.CollectionManager;

/**
 * Executes the "remove_by_id" command
 */
public class Remove extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Remove(CommandLine cl, CollectionManager cm) {
        super("remove_by_id (id)", "Removes an element from collection by its id.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "remove_by_id" command
     *
     * @param arg
     * @param user
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, User user) {
        if(!user.isVerified()) return new Feedbacker(false, ">You need to log in first.", user);
        if(user.getAccess().equals(Access.RESTRICTED_ACCESS)) return new Feedbacker(false, "No permission", user);
        if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.", user);
        try{
            var id = Integer.parseInt(arg.trim());
            Human removeable = null;
            for(var el: cm.getCollection()){if(el.getId()==id) removeable = el;}
            if (removeable==null){return new Feedbacker(false,"No element with such id",user);}
            if(removeable.getOwner().equals(user.getName()) || user.getAccess().equals(Access.FULL_ACCESS)){
                try{
                    cm.removeById(id);
                    return new Feedbacker(">Element removed successfully.", user);
                } catch (NullPointerException e){
                    e.printStackTrace();
                    return new Feedbacker(false, "you're wr0ng.", user);
                }
            } else return new Feedbacker(false,"No permission", user);
        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.", user);}
    }
}
