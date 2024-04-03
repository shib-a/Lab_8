package server.cls.commands;

import org.example.CommandLine;
import org.example.classes.Ask;
import org.example.classes.CollectionManager;
import org.example.classes.Human;

/**
 * Executes the "insert" command
 */
public class Insert extends AbstractCommand {
    private CommandLine cl;
    private CollectionManager cm;
    public Insert(CommandLine cl, CollectionManager cm) {
        super("insert (positive_integer_value)", "Inserts a new element into entered position.");
        this.cl = cl;
        this.cm=cm;
    }
    /**
     * Executes the "insert" command
     * @param arg
     * @return Feedbacker
     */
    @Override
    public Feedbacker execute(String[] arg) {
        try {
            if (arg[1].isEmpty()) return new Feedbacker(false, ">Wrong argument usage. See 'help' for reference.");
            var val = Integer.parseInt(arg[1].trim());
            if(val<=cm.getCollection().size() && !(val<0)){
                cl.printLn(">Creating new Human for insertion:");
                Human h = Ask.askHuman(cl, cm.getUnusedId());
                if (h != null && h.validate()) {
                    cm.insert(val,h);
                    return new Feedbacker(">Inserted successfully.");
                } else return new Feedbacker(false, ">Failed to insert. Invalid arguments.");
            } else return new Feedbacker(false,">Failed. No such index");
        } catch (NumberFormatException|Ask.AskBreaker e) {
            return new Feedbacker(false, ">Failed.");
        }
    }
}
