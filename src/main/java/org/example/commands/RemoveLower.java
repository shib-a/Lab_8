package org.example.commands;

import org.example.CommandLine;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
import org.example.classes.Stat;

public class RemoveLower extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public RemoveLower(CommandLine cl, CollectionManager cm) {
        super("remove_lower (double_value)", "Removes elements with damage value lower than entered");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if(arg[1].isEmpty()) return new Feedbacker(false,"Wrong argument usage. see 'help' for reference");
        try{
            var val = Double.parseDouble(arg[1].trim());
            if (cm.getCollection().isEmpty()){return new Feedbacker("Empty collection");} else{
                for(Human el: cm.getCollection()){
                    if (el.getStat(Stat.DAMAGE)<val) cm.removeById(el.getId());
                }
                return new Feedbacker("Elements removed successfully");}
        } catch(NumberFormatException e){ return new Feedbacker(false,"Wrong argument");}
    }
}
