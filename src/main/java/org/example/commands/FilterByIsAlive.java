package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

public class FilterByIsAlive extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public FilterByIsAlive(CommandLine cl, CollectionManager cm) {
        super("filter_by_is_alive (true/false)", "Shows elements with entered isAlive value");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        try{
            var val = Boolean.parseBoolean(arg[1].trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker("Empty collection");} else{
            for(Human el: cm.getCollection()){
                if (el.isAlive==val) cl.printLn(el);
            }
            return new Feedbacker("Element removed successfully");}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,"Wrong argument");}
    }
}
