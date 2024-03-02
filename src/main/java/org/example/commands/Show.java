package org.example.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;
import org.example.collection.HumanCollection;

public class Show extends AbstractCommand{
    private CommandLine cl;
    private CollectionManager cm;
    public Show(CommandLine cl, CollectionManager cm) {
        super("show", "Shows the elements of collection");
        this.cl = cl;
        this.cm=cm;
    }

    @Override
    public Feedbacker execute(String[] arg) {
        if (!arg[1].isEmpty()) return new Feedbacker(false, "Wrong argument usage. see 'help' for reference");
        if (cm.getCollection().isEmpty()) return new Feedbacker("Collection is empty");
        else{for(Human el:cm.getCollection()){
            cl.printLn(el.toString());
        }
        return new Feedbacker("Elements shown");}
    }
}
