package server.cls.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;

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
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
            if(arg[1].isEmpty()) return new Feedbacker(false,">Wrong argument usage. See 'help' for reference.");
        try{
            var id = Integer.parseInt(arg[1].trim());
            try{cm.removeById(id);}catch (NullPointerException e){return new Feedbacker(">No element with such id.");}
            return new Feedbacker(">Element removed successfully.");
        } catch(NumberFormatException e){ return new Feedbacker(false,">Wrong argument.");}
    }
}
