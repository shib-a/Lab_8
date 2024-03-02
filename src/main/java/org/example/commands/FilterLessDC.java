package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

public class FilterLessDC extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public FilterLessDC(CommandLine cl, CollectionManager cm) {
        super("filter_by_less_than_number_of_dug_counter (positive_integer)", "Shows elements with dugCounter value less than entered");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        try{
            var val = Integer.parseInt(arg[1].trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker("Empty collection");} else{
                Integer count = 0;
                for(Human el: cm.getCollection()){
                    if (el.getDugCounter()<val) {cl.printLn(el);count++;}
                }
                if (count==0) return new Feedbacker("No elements with lower value.");
                return new Feedbacker("Elements showed successfully");}
        } catch(IllegalArgumentException e){ return new Feedbacker(false,"Wrong argument");}
    }
}
