package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

public class Remove extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Remove(CommandLine cl, CollectionManager cm) {
        super("remove_by_id (id)", "Removes an element from collection by its id");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
            if(arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        try{
            var id = Integer.parseInt(arg[1].trim());
            try{cm.removeById(id);}catch (NullPointerException e){return new Feedbacker("No element with such id.");}
            return new Feedbacker("Element removed successfully");
        } catch(NumberFormatException e){ return new Feedbacker(false,"Wrong argument");}
    }
}
