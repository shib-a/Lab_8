package client.commands;
import common.AbstractCommand;
import common.Feedbacker;
import common.UserData;

/**
 * Executes the "remove_by_id" command
 */
public class Remove extends AbstractCommand{
    public Remove() {
        super("remove_by_id (id)", "Removes an element from collection by its id.");
    }
    /**
     * Executes the "remove_by_id" command
     *
     * @param arg
     * @param userData
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String arg, UserData userData) {
//            if(arg.isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
//        try{
//            var id = Integer.parseInt(arg.trim());
//            try{cm.removeById(id);}catch (NullPointerException e){return new Feedbacker(">No element with such id.");}
//            return new Feedbacker(">Element removed successfully.");
//        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.");}
        return new Feedbacker("");
    }
}
